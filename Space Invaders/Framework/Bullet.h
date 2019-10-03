#ifndef __BULLET_H__
#define __BULLET_H__

#include "entity.h"

class Entity;

class Bullet : public Entity
{
	//Member Methods:
public:
	Bullet();
	~Bullet();

	void Process(float deltaTime);

protected:

private:
	Bullet(const Bullet& entity);
	Bullet& operator=(const Bullet& entity);

	//Member Variables
public:

protected:

private:
};

#endif //__BULLET_H__
