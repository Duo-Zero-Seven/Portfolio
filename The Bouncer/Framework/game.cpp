
// COMP710 GP 2D Framework 2019

// This includes:
#include "game.h"

// Local includes:
#include "backbuffer.h"
#include "inputhandler.h"
#include "logmanager.h"
#include "sprite.h"
#include "entity.h"

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
	delete m_pInputHandler;
	m_pInputHandler = 0;
}

bool 
Game::Initialise()
{
	const int width = 1920;
	const int height = 1080;

	m_pBackBuffer = new BackBuffer();
	if (!m_pBackBuffer->Initialise(width, height))
	{
		LogManager::GetInstance().Log("BackBuffer Init Fail!");
		return (false);
	}
	bouncerSprite = m_pBackBuffer->CreateSprite("assets/playership.png");
	m_pBouncer = new Entity;
	m_pBouncer->Initialise(bouncerSprite);
	
	m_pInputHandler = new InputHandler();
	if (!m_pInputHandler->Initialise())
	{
		LogManager::GetInstance().Log("InputHandler Init Fail!");
		return (false);
	}

	m_lastTime = SDL_GetPerformanceCounter();
	m_lag = 0.0f;

	m_pBackBuffer->SetClearColour(0xCC, 0xCC, 0xCC);

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

		Process(deltaTime);
	
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
	m_pBouncer->Process(deltaTime);
	
}

void 
Game::Draw(BackBuffer& backBuffer)
{
	++m_frameCount;
	x_increment = 100;
	lineIncrementPosX = 100;
	lineIncrementPosY = 100;
	lineIncrementNegX = 0;
	lineIncrementNegY = 0;
	lineIncrementCentre = 50;
	x_incrementOffset = 0;
	y_increment = 100;
	y_incrementOffset = 0;
	backBuffer.Clear();
	
	//backBuffer.SetTextColour(255, 0, 0);
	//backBuffer.DrawText(50, 50, "Hello COMP710");
	backBuffer.SetDrawColour(255, 0, 0);
	backBuffer.DrawRectangle(100, 100, 500, 500);
	
	for (int h = 0; h < 12; h++)
	{
		for (int i = 0; i < 16; i++)
		{
			if ((i % 2 == 0 && h == 0) || (i % 2 == 0 && h % 2 == 0) && h < 10)
			{
				backBuffer.SetDrawColour(255, 0, 0);
				x_increment = (100 * i);
				backBuffer.DrawRectangle(100 + x_increment, 0 + y_increment, 200 + x_increment, 100 + y_increment);
				backBuffer.SetDrawColour(0, 255, 0);
				x_incrementOffset = (100 * i);
				backBuffer.DrawRectangle(100 + x_incrementOffset, 100 + y_incrementOffset, 200 + x_incrementOffset, 200 + y_incrementOffset);
			}
			else if((i == 1 || i == 3 || i == 5 || i == 7 || i == 9 || i == 11 || i == 13) && 
					(h == 2 || h == 4 || h == 6 || h == 8))
			{
				backBuffer.SetDrawColour(255, 0, 0);
				x_incrementOffset = (100 * i);
				backBuffer.DrawRectangle(100 + x_incrementOffset, 100 + y_incrementOffset, 200 + x_incrementOffset, 200 + y_incrementOffset);
				backBuffer.SetDrawColour(0, 255, 0);
				x_increment = (100 * i);
				backBuffer.DrawRectangle(100 + x_increment, 0 + y_increment, 200 + x_increment, 100 + y_increment);
			}
		}
		y_increment = (100 * h);
		y_incrementOffset = (100 * h);
	}
	for (int j = 0; j < 8; j++)
	{
		for (int k = 0; k < 15; k++)
		{
			backBuffer.SetDrawColour(0, 0, 0);
			lineIncrementPosX = (100 * k);
			backBuffer.DrawLine(m_pBouncer->GetPositionX() + 16, m_pBouncer->GetPositionY() + 16, 100 + lineIncrementPosX + 50, 100 + lineIncrementPosY + 50);
			backBuffer.SetDrawColour(0, 0, 0);
			lineIncrementNegX = (100 * k);
			backBuffer.DrawLine(m_pBouncer->GetPositionX() + 16, m_pBouncer->GetPositionY() + 16, 100 + lineIncrementNegX + 50, 200 + lineIncrementNegY + 50);
		}
		lineIncrementPosY = (100 * j);
		lineIncrementNegY = (100 * j);
	}

	backBuffer.SetDrawColour(0, 0, 0);
	backBuffer.DrawLine(m_pBouncer->GetPositionX() + 16, m_pBouncer->GetPositionY() + 16, 250, 150);
	m_pBouncer->Draw(backBuffer);
	
	backBuffer.Present();
}

void 
Game::Quit()
{
	m_looping = false;
}