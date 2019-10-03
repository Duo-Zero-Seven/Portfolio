// COMP710 GP 2D Framework 2019
#ifndef __GAME_H__
#define __GAME_H__
#include <vector>

// Forward Declarations
class BackBuffer;
class InputHandler;
class Sprite;
class AnimatedSprite;
class PlayerShip;
class Enemy;
class Bullet;
class Explosion;

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

	void MoveSpaceShipLeft();
	void MoveSpaceShipRight();
	void SpaceShipHalt();
	void FireSpaceShipBullet();

	void SpawnEnemy(int x, int y);
	void SpawnExplosions(int x, int y);

	BackBuffer * GetBackBuffer();

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
	
	// Game Entities:

	// SS04.4: Add a PlayerShip field. 
	PlayerShip* m_playerShip;
	// SS04.5: Add an alien enemy container field.
	std::vector<Enemy*> m_enemies;
	// SS04.6: Add a bullet container field.
	std::vector<Bullet*> m_bullets;
	// Explosion Container field
	std::vector<Explosion*> m_explosions;
	// Animated sprite
	AnimatedSprite* m_animatedSprite;
	
private:

};

#endif // __GAME_H__
