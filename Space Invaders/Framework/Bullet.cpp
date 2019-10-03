// This includes:
#include "Bullet.h"

Bullet::Bullet()
{
}

Bullet::~Bullet()
{
}

void 
Bullet::Process(float deltaTime)
{
	m_x = m_x + (m_velocityX * deltaTime);
	m_y = m_y + (m_velocityY * deltaTime);

	if (m_y < 64)
	{
		m_dead = true;
	}
}
