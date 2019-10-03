#ifndef __EXPLOSION_H__
#define __EXPLOSION_H__

#include "entity.h"

class Entity;
class AnimatedSprite;

class Explosion : public Entity
{
	//Member Methods:
public:
	Explosion(int x, int y);
	~Explosion();
	void Process(float deltaTime);
	void Draw(BackBuffer& backBuffer);

protected:

private:
	Explosion(const Explosion& entity);
	Explosion& operator=(const Explosion& entity);
	

	//Member Variables
public:

protected:
	AnimatedSprite* m_pAnimatedSprite;

private:
};

#endif //__EXPLOSION_H__