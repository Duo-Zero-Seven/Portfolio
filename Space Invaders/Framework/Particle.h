#ifndef __PARTICLE_H__
#define __PARTICLE_H__

#include "entity.h"

class Entity;

class Particle : public Entity
{
	//Member Methods:
public:
	Particle();
	~Particle();

	void Process(float deltaTime);

protected:

private:
	Particle(const Particle& entity);
	Particle& operator=(const Particle& entity);

	//Member Variables
public:

protected:

private:
};

#endif //__PARTICLE_H__