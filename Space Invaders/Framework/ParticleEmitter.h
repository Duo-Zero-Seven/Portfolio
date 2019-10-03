#ifndef __PARTICLEEMITTER_H__
#define __PARTICLEEMITTER_H__

#include "entity.h"

class Entity;

class ParticleEmitter : public Entity
{
	//Member Methods:
public:
	ParticleEmitter();
	~ParticleEmitter();

	void Process(float deltaTime);

protected:

private:
	ParticleEmitter(const ParticleEmitter& entity);
	ParticleEmitter& operator=(const ParticleEmitter& entity);

	//Member Variables
public:

protected:

private:
};

#endif //__PARTICLEEMITTER_H__