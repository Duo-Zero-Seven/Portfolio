// This includes:
#include "PlayerShip.h"

// Local includes:
#include "sprite.h"
#include "backbuffer.h"

// Library includes:
#include <cassert>

PlayerShip::PlayerShip()
{
	SetPositionX(824);
	SetPositionY(1018);
}

PlayerShip::~PlayerShip()
{
}

void 
PlayerShip::Process(float deltaTime)
{
	m_x = m_x + (m_velocityX * deltaTime);
	m_y = m_y + (m_velocityY * deltaTime);
	
	if (m_x > 1648 || m_x < 0)
	{
		if (m_x > 1648)
		{
			m_x = 1648;
		}

		if (m_x < 0)
		{
			m_x = 0;
		}
	}
}