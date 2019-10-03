// COMP710 GP 2D Framework 2019

// This include:
#include "entity.h"

// Local includes:
#include "sprite.h"
#include "backbuffer.h"

// Library includes:
#include <cassert>

Entity::Entity()
: m_pSprite(0)
, m_x(0.0f)
, m_y(0.0f)
, m_velocityX(0.0f)
, m_velocityY(0.0f)
, m_dead(false)
, m_boundaryX(true)
, m_boundaryY(true)
{

}

Entity::~Entity()
{

}

bool
Entity::Initialise(Sprite* sprite)
{
	assert(sprite);
	m_pSprite = sprite;
	return (true);
}

void 
Entity::Process(float deltaTime)
{
	m_velocityX = 80;
	m_velocityY = 80;
	
	
	if (m_x < 1648 && m_x >= 0)
	{
		if(m_boundaryX)
			m_x = m_x - (m_velocityX * deltaTime);
		else if(!m_boundaryX)
			m_x = m_x + (m_velocityX * deltaTime);
	}
	else if (m_x > 1648 || m_x < 0)
	{
		if (m_x > 1648)
		{
			m_boundaryX = true;
			m_x = m_x - (m_velocityX * deltaTime);
		}

		if (m_x < 0)
		{
			m_boundaryX = false;
			m_x = m_x + (m_velocityX * deltaTime);
		}	
	}
	
	if (m_y < 1018 && m_y >= 0)
	{
		if(m_boundaryY)
			m_y = m_y - (m_velocityY * deltaTime);
		else if(!m_boundaryY)
			m_y = m_y + (m_velocityY * deltaTime);
	}
	else if (m_y > 1018 || m_y < 0)
	{
		if (m_y > 1018)
		{
			m_boundaryY = true;
			m_y = m_y - (m_velocityY * deltaTime);
		}

		if (m_y < 0)
		{
			m_boundaryY = false;
			m_y = m_y + (m_velocityY * deltaTime);
		}	
	}
}

void 
Entity::Draw(BackBuffer& backBuffer)
{
	assert(m_pSprite);
	m_pSprite->SetX(static_cast<int>(m_x));
	m_pSprite->SetY(static_cast<int>(m_y));
	m_pSprite->Draw(backBuffer);
}

bool
Entity::IsCollidingWith(Entity& e)
{
	return (false);
}

void 
Entity::SetDead(bool dead)
{
	m_dead = dead;
}

void
Entity::SetPosition(float x, float y)
{
	m_x = x;
	m_y = y;
}

void
Entity::SetPositionX(float x)
{
	m_x = x;
}

void
Entity::SetPositionY(float y)
{
	m_y = y;
}

float 
Entity::GetPositionX() const
{
	return (m_x);
}

float 
Entity::GetPositionY() const
{
	return (m_y);
}

float 
Entity::GetHorizontalVelocity() const
{
	return (m_velocityX);
}

float 
Entity::GetVerticalVelocity() const
{
	return (m_velocityY);
}

void 
Entity::SetHorizontalVelocity(float x)
{
	m_velocityX = x;
}

void 
Entity::SetVerticalVelocity(float y)
{
	m_velocityY = y;
}
