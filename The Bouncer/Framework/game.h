// COMP710 GP 2D Framework 2019
#ifndef __GAME_H__
#define __GAME_H__

// Forward Declarations
class BackBuffer;
class InputHandler;
class Sprite;
class Entity;

class Game
{
	//Member Methods:
public:
	static Game& GetInstance();
	static void DestroyInstance();
	~Game();

	bool Initialise();
	bool DoGameLoop();
	void Quit();

protected:
	void Process(float deltaTime);
	void Draw(BackBuffer& backBuffer);

private:
	Game(const Game& game);
	Game& operator=(const Game& game);
	
	Game();

	//Member Data:
public:

protected:
	static Game* sm_pInstance;
	BackBuffer* m_pBackBuffer;
	InputHandler* m_pInputHandler;
	Sprite* bouncerSprite;
	bool m_looping;

	// Simulation Counters:
	float m_elapsedSeconds;
	float m_lag;
	float m_executionTime;
	__int64 m_lastTime;
	int m_frameCount;
	int m_FPS;
	int m_numUpdates;
	bool m_drawDebugInfo;
	Entity* m_pBouncer;
	int velocityX;

	//draw checkerboard increments
	//int m_checkX;
	//int m_checkY;
	int x_increment;
	int x_incrementOffset;
	int y_increment;
	int y_incrementOffset;
	int lineIncrementPosX = 100;
	int lineIncrementPosY = 100;
	int lineIncrementNegX = 0;
	int lineIncrementNegY = 0;
	int lineIncrementCentre = 50;

private:

};

#endif // __GAME_H__
