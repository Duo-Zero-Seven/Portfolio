// COMP710 GP 2D Framework 2019

#ifndef __ANIMATEDSPRITE_H__
#define __ANIMATEDSPRITE_H__

#include <vector>
#include "sprite.h"

class BackBuffer;
class Texture;

class AnimatedSprite : public Sprite
{
	// Methods:
public:
	AnimatedSprite();
	~AnimatedSprite();

	bool Initialise(Texture& texture);
	bool Initialise();
	void AddFrame(int x);

	void Process(float deltaTime);
	void Draw(BackBuffer& backbuffer);

	void SetFrameSpeed(float f);
	void SetFrameWidth(int w);

	int GetFrameWidth() const;

	void Pause();
	bool IsPaused();

	bool IsAnimating();
	void StartAnimating();

	bool IsLooping();
	void SetLooping(bool b);

protected:

private:

	// Fields:
public:

protected:
	// SS05.5: Add a field, the container for frame coordinates.
	
	float m_frameSpeed;
	int m_frameWidth;

	float m_timeElapsed;
	int m_currentFrame;

	bool m_paused;
	bool m_loop;
	bool m_animating;

	std::vector<int> frameCoordinates;

private:

};

#endif // __ANIMATEDSPRITE_H__
