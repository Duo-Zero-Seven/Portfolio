// COMP710 GP 2D Framework 2019

// This includes:
#include "game.h"

// Local includes:
#include "backbuffer.h"
#include "inputhandler.h"
#include "logmanager.h"
#include "sprite.h"
#include "animatedsprite.h"
#include "entity.h"
#include "PlayerShip.h"
#include "Enemy.h"
#include "Bullet.h"
#include "Explosion.h"
#include "vector"
// Library includes:
#include <cassert>
#include <SDL.h>
#include <cstdio>

// Static Members:
Game* Game::sm_pInstance = 0;

Game&
Game::GetInstance()
{
	if (sm_pInstance == 0)
	{ 
		sm_pInstance = new Game();
	}

	assert(sm_pInstance);

	return (*sm_pInstance);
}

void 
Game::DestroyInstance()
{
	delete sm_pInstance; 
	sm_pInstance = 0;
}

Game::Game()
: m_pBackBuffer(0)
, m_pInputHandler(0)
, m_looping(true)
, m_executionTime(0)
, m_elapsedSeconds(0)
, m_frameCount(0)
, m_FPS(0)
, m_numUpdates(0)
, m_lastTime(0)
, m_lag(0)
{
	
}

Game::~Game()
{
	delete m_pBackBuffer;
	m_pBackBuffer = 0;
}

bool 
Game::Initialise()
{
	const int width = 1680;
	const int height = 1050;

	m_pBackBuffer = new BackBuffer();
	if (!m_pBackBuffer->Initialise(width, height))
	{
		LogManager::GetInstance().Log("BackBuffer Init Fail!");
		return (false);
	}

	m_pInputHandler = new InputHandler();
	if (!m_pInputHandler->Initialise())
	{
		LogManager::GetInstance().Log("InputHandler Init Fail!");
		return (false);
	}

	m_lastTime = SDL_GetPerformanceCounter();
	m_lag = 0.0f;

	m_pBackBuffer->SetClearColour(0xCC, 0xCC, 0xCC);

	// SS04.4: Load the player ship sprite.
	// For example: Sprite* pPlayerSprite = m_pBackBuffer->CreateSprite("assets\\playership.png");
	// But pPlayerSprite should be a member of the Game class...
	// ...not just a local variable of this function!

	// SS04.4: Create the player ship instance.
	//Initialise Player Ship
	m_playerShip = new PlayerShip();
	Sprite* playerShipSprite = m_pBackBuffer->CreateSprite("assets\\playership.png");
	m_playerShip->Initialise(playerShipSprite);

	// SS04.5: Spawn four rows of 14 alien enemies.
	for (int j = 0; j < 4; j++)
	{
		for (int i = 0; i < 14; i++)
		{
			SpawnEnemy((140 + (100 * i)), (100 + (100 * j)));
		}
	}
	
	// SS04.6: Fill the container with these new enemies.
	
	return (true);
}

bool 
Game::DoGameLoop()
{
	const float stepSize = 1.0f / 60.0f;

	assert(m_pInputHandler);
	m_pInputHandler->ProcessInput(*this);
	
	if (m_looping)
	{
		Uint64 current = SDL_GetPerformanceCounter();

		float deltaTime = (current - m_lastTime) / static_cast<float>(SDL_GetPerformanceFrequency());
		
		m_lastTime = current;

		m_executionTime += deltaTime;

		//Process(deltaTime);
	
		m_lag += deltaTime;

		int innerLag = 0;

		while (m_lag >= stepSize)
		{
			Process(stepSize);

			m_lag -= stepSize;

			++m_numUpdates;
			++innerLag;
		}

		// DEBUG STUB:
//		char buffer[64];
//		sprintf(buffer, "%f", deltaTime);
//		LogManager::GetInstance().Log(buffer);

		Draw(*m_pBackBuffer);
	}

	return (m_looping);
}

void 
Game::Process(float deltaTime)
{
	// Count total simulation time elapsed:
	m_elapsedSeconds += deltaTime;

	// Frame Counter:
	if (m_elapsedSeconds > 1)
	{
		m_elapsedSeconds -= 1;
		m_FPS = m_frameCount;
		m_frameCount = 0;
	}

	// Update the game world simulation:
	
	// SS04.5: Process each alien enemy in the container.
	std::vector<Enemy*>::iterator itE = m_enemies.begin();
	while (itE != m_enemies.end())
	{
		Enemy* e = *itE;
		if (!e->IsDead())
		{
			e->Process(deltaTime);
			itE++;
		}
		else
		{
			itE = m_enemies.erase(itE);
		}
	}
	// SS04.6: Process each bullet in the container.
	std::vector<Bullet*>::iterator itB = m_bullets.begin();
	while (itB != m_bullets.end())
	{
		Bullet* b = *itB;
		if (!b->IsDead())
		{
			b->Process(deltaTime);
			itB++;
		}
		else
		{
			itB = m_bullets.erase(itB);
		}
	}
	//Process all explosions in container
	std::vector<Explosion*>::iterator itEx = m_explosions.begin();
	while (itEx != m_explosions.end())
	{
		Explosion* ex = *itEx;
		if (!ex->IsDead())
		{
			ex->Process(deltaTime);
			itEx++;
		}
		else
		{
			itEx = m_explosions.erase(itEx);
		}
	}
	// SS04.4: Update player...
	m_playerShip->Process(deltaTime);

	// SS04.6: Check for bullet vs alien enemy collisions...
	// SS04.6: For each bullet
	// SS04.6: For each enemy
	// SS04.6: Check collision between two entities.
	// SS04.6: If collided, destory both and spawn explosion.
	// SS04.6: Remove any dead bullets from the container...
	// SS04.6: Remove any dead enemy aliens from the container...
	// SS04.6: Remove any dead explosions from the container...
	for (int i = 0; i < m_bullets.size(); ++i)
	{
		Bullet* bullet = m_bullets.at(i);
		for (int j = 0; j < m_enemies.size(); ++j)
		{
			Enemy* enemy = m_enemies.at(j);
			if (bullet->IsCollidingWith(*enemy) && enemy->IsCollidingWith(*bullet))
			{
				SpawnExplosions(enemy->GetPositionX() - 16, enemy->GetPositionY() - 16);
				
				enemy->SetDead(true);
				bullet->SetDead(true);
			}
		}
	}
}

void 
Game::Draw(BackBuffer& backBuffer)
{
	++m_frameCount;
	//Counts to make sure creation and deletion of objects running correctly.
	char bulletTracker[64];
	sprintf(bulletTracker, "%d", m_bullets.size());
	backBuffer.Clear();
	backBuffer.SetTextColour(255, 0, 0);
	backBuffer.DrawText(50, 50, "Bullets:");
	backBuffer.DrawText(90, 50, bulletTracker);
	char enemyTracker[64];
	sprintf(enemyTracker, "%d", m_enemies.size());
	backBuffer.SetTextColour(255, 0, 0);
	backBuffer.DrawText(50, 60, "Enemies Remaining::");
	backBuffer.DrawText(155, 60, enemyTracker);
	char explosionTracker[64];
	sprintf(explosionTracker, "%d", m_explosions.size());
	backBuffer.SetTextColour(255, 0, 0);
	backBuffer.DrawText(50, 70, "Explosions Remaining::");
	backBuffer.DrawText(165, 70, explosionTracker);
	// SS04.5: Draw all enemy aliens in container...
	std::vector<Enemy*>::iterator itE = m_enemies.begin();
	while (itE != m_enemies.end())
	{
		(*itE)->Draw(backBuffer);
		itE++;
	}
	// SS04.6: Draw all bullets in container...
	std::vector<Bullet*>::iterator itB = m_bullets.begin();
	while (itB != m_bullets.end())
	{
		(*itB)->Draw(backBuffer);
		itB++;
	}

	//Draw all explosions in container
	std::vector<Explosion*>::iterator itEx = m_explosions.begin();
	while (itEx != m_explosions.end())
	{
		(*itEx)->Draw(backBuffer);
		itEx++;
	}
	// SS04.4: Draw the player ship...
	m_playerShip->Draw(backBuffer);
	
	backBuffer.Present();
}

void 
Game::Quit()
{
	m_looping = false;
}

void
Game::MoveSpaceShipLeft()
{
	// SS04.3: Add this method's prototype to the Game.h file.
	// SS04.3: Tell the player ship to move left.
	m_playerShip->SetHorizontalVelocity(-150); 
}

// SS04.3: Add the method to tell the player ship to move right...
void
Game::MoveSpaceShipRight()
{
	// SS04.3: Add this method's prototype to the Game.h file.
	// SS04.3: Tell the player ship to move left.
	m_playerShip->SetHorizontalVelocity(+150);
}

void
Game::SpaceShipHalt()
{
	// SS04.3: Add this method's prototype to the Game.h file.
	// SS04.3: Tell the player ship to move left.
	m_playerShip->SetHorizontalVelocity(0);
}

// SS04.6: Space fires a Bullet in game.
void
Game::FireSpaceShipBullet()
{
	// SS04.6: Add this method's prototype to the Game.h file.
	// SS04.6: Load the player bullet sprite.      
	// SS04.6: Create a new bullet object.
	// SS04.6: Set the bullets vertical velocity.
	// SS04.6: Add the new bullet to the bullet container.
	Bullet* bullet = new Bullet();
	Sprite* bulletSprite = m_pBackBuffer->CreateSprite("assets\\playerbullet.png");
	bullet->Initialise(bulletSprite);
	bullet->SetPositionX(m_playerShip->GetPositionX());
	bullet->SetPositionY(m_playerShip->GetPositionY());
	bullet->SetVerticalVelocity(-300);
	m_bullets.push_back(bullet);
}

// SS04.5: Spawn a Enemy in game.
void
Game::SpawnEnemy(int x, int y)
{
	// SS04.5: Add this method's prototype to the Game.h file.
	// SS04.5: Load the alien enemy sprite file.
	Sprite* enemySprite = m_pBackBuffer->CreateSprite("assets\\alienenemy.png");
	// SS04.5: Create a new Enemy object. 
	Enemy* enemy = new Enemy();
	enemy->Initialise(enemySprite);
	enemy->SetPositionX(x);
	enemy->SetPositionY(y);
	// SS04.5: Add the new Enemy to the enemy container.
	m_enemies.push_back(enemy);
}

void
Game::SpawnExplosions(int x, int y)
{
	//Create Explosion at coordinates
	Explosion* explosion = new Explosion(x, y);
	//Add the new Explosion to the explosion container.
	m_explosions.push_back(explosion);
}

BackBuffer*
Game::GetBackBuffer()
{
	return m_pBackBuffer;
}
