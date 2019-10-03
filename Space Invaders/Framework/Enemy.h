#ifndef __ENEMY_H__
#define __ENEMY_H__

#include "entity.h"

class Entity;

class Enemy : public Entity
{
	//Member Methods:
public:
	Enemy();
	~Enemy();

protected:

private:
	Enemy(const Enemy& entity);
	Enemy& operator=(const Enemy& entity);

	//Member Variables
public:

protected:

private:
};

#endif //__ENEMY_H__
