// COMP710 GP 2D Framework 2019
#ifndef __BACKBUFFER_H__
#define __BACKBUFFER_H__

#include "SDL_ttf.h"

// Forward Declarations:
class TextureManager;
class Sprite;
class AnimatedSprite;
struct SDL_Window;
struct SDL_Renderer;

class BackBuffer
{
	//Member Methods:
public:
	BackBuffer();
	~BackBuffer();

	bool Initialise(int width, int height);

	void Clear();
	void Present();

	void SetFullscreen(bool fullscreen);

	void SetClearColour(unsigned char r, unsigned char g, unsigned char b);

	void SetDrawColour(unsigned char r, unsigned char g, unsigned char b);
	void SetDrawColour(unsigned char r, unsigned char g, unsigned char b, unsigned char a);

	void DrawSprite(Sprite& sprite);
	void DrawAnimatedSprite(Sprite& sprite, int);
	void DrawRectangle(int x1, int y1, int x2, int y2);
	void DrawLine(int x1, int y1, int x2, int y2);

	Sprite* CreateSprite(const char* pcFilename);

	AnimatedSprite * CreateAnimatedSprite(const char * pcFilename, int frames, int framewidth);

	//SDL_ttf methods
	void SetTextColour(unsigned char r, unsigned char g, unsigned char b);
	void DrawText(int x, int y, const char* pcText);
	
protected:
	void LogSDLError();

private:
	BackBuffer(const BackBuffer& backBuffer);
	BackBuffer& operator=(const BackBuffer& backBuffer);

	//Member Data:
public:

protected:
	TextureManager* m_pTextureManager;

	SDL_Window* m_pWindow;
	SDL_Renderer* m_pRenderer;

	int m_width;
	int m_height;

	unsigned char m_clearRed;
	unsigned char m_clearGreen;
	unsigned char m_clearBlue;

	//SDL_ttf members
	TTF_Font* m_pDebugFont;
	unsigned char m_textRed;
	unsigned char m_textGreen;
	unsigned char m_textBlue;
	
private:

};

#endif // __BACKBUFFER_H__
