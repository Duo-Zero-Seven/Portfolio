#ifndef __PLAYERBULLETTRAILPARTICLE_H__
#define __PLAYERBULLETTRAILPARTICLE_H__

#include "Particle.h"
//https://natureofcode.com/book/chapter-4-particle-systems/
class Particle;

class PlayerBulletTrailParticle : public Particle
{
	//Member Methods:
public:
	PlayerBulletTrailParticle();
	~PlayerBulletTrailParticle();

	void Process(float deltaTime);

protected:

private:
	PlayerBulletTrailParticle(const PlayerBulletTrailParticle& entity);
	PlayerBulletTrailParticle& operator=(const PlayerBulletTrailParticle& entity);

	//Member Variables
public:

protected:

private:
};

#endif //__PLAYERBULLETTRAILPARTICLE_H__