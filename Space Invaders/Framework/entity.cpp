// COMP710 GP 2D Framework 2019

// This include:
#include "entity.h"

// Local includes:
#include "sprite.h"
#include "backbuffer.h"
#include "logmanager.h"
// Library includes:
#include <cassert>

Entity::Entity()
: m_pSprite(0)
, m_x(0.0f)
, m_y(0.0f)
, m_velocityX(0.0f)
, m_velocityY(0.0f)
, m_dead(false)
{

}

Entity::~Entity()
{
	if (m_dead)
	{
		delete m_pSprite;
		LogManager::GetInstance().Log("Entity Dead");
		m_pSprite = 0;
	}
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
	// SS04.4: Generic position update, based upon velocity (and time).
	m_x = m_x + (m_velocityX * deltaTime);
	m_y = m_y + (m_velocityY * deltaTime);
	// SS04.4: Boundary checking and position capping. 
}

void 
Entity::Draw(BackBuffer& backBuffer)
{
	if (!m_dead)
	{
		assert(m_pSprite);
		m_pSprite->SetX(static_cast<int>(m_x));
		m_pSprite->SetY(static_cast<int>(m_y));
		m_pSprite->Draw(backBuffer);
	}
}

bool
Entity::IsCollidingWith(Entity& e)
{
	// SS04.6: Generic Entity Collision routine.
	// SS04.6 Change return value!
	// SS04.6: Does this object collide with the e object?
	// SS04.6: Create a circle for each entity (this and e).

	// SS04.6: Check for intersection.
	// SS04.6: Using circle-vs-circle collision detection.

	// SS04.6: Return result of collision.
	if (!m_dead)
	{
		float distance = sqrtf(powf(m_x - e.GetPositionX(), 2) + powf(m_y - e.GetPositionY(), 2));
		return distance < ((m_pSprite->GetWidth() / 2) + (e.m_pSprite->GetWidth() / 2));
	}
	else
	{
		return 0;
	}
	
}

void 
Entity::SetDead(bool dead)
{
	m_dead = dead;
}

bool Entity::IsDead() const
{
	return m_dead;
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
