#ifndef __PLAYERSHIP_H__
#define __PLAYERSHIP_H__

#include "entity.h"

class Entity;

class PlayerShip : public Entity
{
	//Member Methods:
public:
	PlayerShip();
	~PlayerShip();

	void Process(float deltaTime);

protected:

private:
	PlayerShip(const PlayerShip& entity);
	PlayerShip& operator=(const PlayerShip& entity);

	//Member Variables
public:
	
protected:
	bool m_boundaryX;
	bool m_boundaryY;

private:

};

#endif //__PLAYERSHIP_H__