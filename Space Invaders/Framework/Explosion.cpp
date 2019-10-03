//This includes:
#include "Explosion.h"
#include "animatedsprite.h"
#include "backbuffer.h"
#include "game.h"

Explosion::Explosion(int x, int y)
{
	m_pAnimatedSprite = Game::GetInstance().GetBackBuffer()->CreateAnimatedSprite("assets\\explosion.png", 5, 64);
	m_pAnimatedSprite->Initialise();
	m_pAnimatedSprite->SetX(x);
	m_pAnimatedSprite->SetY(y);
}

Explosion::~Explosion()
{
}

void Explosion::Process(float deltaTime)
{
	if (!m_pAnimatedSprite->IsPaused())
		m_pAnimatedSprite->Process(deltaTime);
	else
		SetDead(true);
}

void Explosion::Draw(BackBuffer & backBuffer)
{
	m_pAnimatedSprite->Draw(backBuffer);
}