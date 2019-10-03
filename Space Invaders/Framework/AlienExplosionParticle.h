#ifndef __ALIENEXPLOSIONPARTICLE_H__
#define __ALIENEXPLOSIONPARTICLE_H__

#include "Particle.h"

class Particle;

class AlienExplosionParticle : public Particle
{
	//Member Methods:
public:
	AlienExplosionParticle();
	~AlienExplosionParticle();

	void Process(float deltaTime);

protected:

private:
	AlienExplosionParticle(const AlienExplosionParticle& entity);
	AlienExplosionParticle& operator=(const AlienExplosionParticle& entity);

	//Member Variables
public:

protected:

private:
};

#endif //__ALIENEXPLOSIONPARTICLE_H__