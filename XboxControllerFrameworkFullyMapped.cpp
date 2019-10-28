// COMP710 GP 2D Framework 2019

// This include:
#include "inputhandler.h"

// Local includes:
#include "game.h"
#include "logmanager.h"

// Library includes:
#include <cassert>
#include <cstdio>

// DEBUG STUB:
//		char buffer[64];
//		sprintf(buffer, "%f", deltaTime);
//		LogManager::GetInstance().Log(buffer);

InputHandler::InputHandler()
: m_pGameController(0)
{

}

InputHandler::~InputHandler()
{
	if (m_pGameController)
	{
		SDL_JoystickClose(m_pGameController);
		m_pGameController = 0;
	}
}

bool 
InputHandler::Initialise()
{
	int numControllesr = SDL_NumJoysticks();

	m_pGameController = SDL_JoystickOpen(0);

	if (!m_pGameController)
	{
		LogManager::GetInstance().Log("No controller detected!");
	}

	return (true);
}

void
InputHandler::ProcessInput(Game& game)
{
	SDL_Event event;

	while (SDL_PollEvent(&event) != 0)
	{
		//KEYBOARD TREE
		if (event.type == SDL_QUIT)
		{
			game.Quit();
		}
		else if (event.type == SDL_KEYDOWN) //When key pressed
		{
			if (event.key.keysym.sym == SDLK_ESCAPE)
			{
				game.Quit();
			}

			if (event.key.keysym.sym == SDLK_LEFT)
			{
				LogManager::GetInstance().Log("Left!");
			}

			if (event.key.keysym.sym == SDLK_RIGHT)
			{
				LogManager::GetInstance().Log("Right!");
			}

			if (event.key.keysym.sym == SDLK_UP)
			{
				LogManager::GetInstance().Log("Up!");
			}

			if (event.key.keysym.sym == SDLK_DOWN)
			{
				LogManager::GetInstance().Log("Down!");
			}

		}
		else if (event.type == SDL_KEYUP) //When key released
		{
			if (event.key.keysym.sym == SDLK_LEFT)
			{
				LogManager::GetInstance().Log("Left Stop!");
			}

			if (event.key.keysym.sym == SDLK_RIGHT)
			{
				LogManager::GetInstance().Log("Right Stop!");
			}

			if (event.key.keysym.sym == SDLK_UP)
			{
				LogManager::GetInstance().Log("Up Stop!");
			}

			if (event.key.keysym.sym == SDLK_DOWN)
			{
				LogManager::GetInstance().Log("Down Stop!");
			}
		}

		//CONTROLLER STICK AXIS TREE
		if (event.type == SDL_JOYAXISMOTION)
		{
			//JOY AXIS MOTION KEY
			//LEFT STICK
			//0 = LEFT/RIGHT Axis
			//0 || <3200 LEFT
			//0 || >3200 RIGHT
			//1 = UP/DOWN Axis
			//1 || <3200 UP
			//1 || >3200 DOWN

			//RIGHT STICK
			//3 = LEFT/RIGHT Axis
			//3 || <3200 LEFT
			//3 || >3200 RIGHT
			//4 = UP/DOWN Axis
			//4 || <3200 UP
			//4 || >3200 DOWN

			//LEFT TRIGGER
			//2 || <3200 Released
			//2 || >3200 Depressed

			//RIGHT TRIGGER
			//5 || <3200 Released
			//5 || >3200 Depressed

			if ((event.jaxis.value < -3200) || (event.jaxis.value > 3200))
			{
				if (event.jaxis.axis == 0) //Left-Right Movement axis set (Left Thumbstick)
				{
					/* Left-right movement code goes here */
					if (event.jaxis.value < -12800) //if value outside of deadzone
					{
						LogManager::GetInstance().Log("Left Thumbstick Left!");
					}
					else if (event.jaxis.value > -12800 && event.jaxis.value <= 0) //if value inside of deadzone
					{
						LogManager::GetInstance().Log("Left Thumbstick Left Released!");
					}

					if (event.jaxis.value > 12800) //if value outside of deadzone
					{
						LogManager::GetInstance().Log("Left Thumbstick Right!");
					}
					else if (event.jaxis.value < 12800 && event.jaxis.value >= 0) //if value inside of deadzone
					{
						LogManager::GetInstance().Log("Left Thumbstick Right Released!");
					}
				}

				if (event.jaxis.axis == 1) //Up-Down Movement axis set (Left Thumbstick)
				{
					/* Up-Down movement code goes here */
					if (event.jaxis.value < -12800) //if value outside of deadzone
					{
						LogManager::GetInstance().Log("Left Thumbstick Up!");
					}
					else if (event.jaxis.value > -12800 && event.jaxis.value <= 0) //if value inside of deadzone
					{
						LogManager::GetInstance().Log("Left Thumbstick Up Released!");
					}

					if (event.jaxis.value > 12800) //if value outside of deadzone
					{
						LogManager::GetInstance().Log("Left Thumbstick Down!");
					}
					else if (event.jaxis.value < 12800 && event.jaxis.value >= 0) //if value inside of deadzone
					{
						LogManager::GetInstance().Log("Left Thumbstick Down Released!");
					}
				}

				if (event.jaxis.axis == 3) //Left-Right Movement axis set (Right Thumbstick)
				{
					/* Left-right movement code goes here */
					if (event.jaxis.value < -12800) //if value outside of deadzone
					{
						LogManager::GetInstance().Log("Right Thumbstick Left!");
					}
					else if (event.jaxis.value > -12800 && event.jaxis.value <= 0) //if value inside of deadzone
					{
						LogManager::GetInstance().Log("Right Thumbstick Left Released!");
					}

					if (event.jaxis.value > 12800) //if value outside of deadzone
					{
						LogManager::GetInstance().Log("Right Thumbstick Right!");
					}
					else if (event.jaxis.value < 12800 && event.jaxis.value >= 0) //if value inside of deadzone
					{
						LogManager::GetInstance().Log("Right Thumbstick Right Released!");
					}
				}

				if (event.jaxis.axis == 4) //Up-Down Movement axis set (Right Thumbstick)
				{
					/* Up-Down movement code goes here */
					if (event.jaxis.value < -12800) //if value outside of deadzone
					{
						LogManager::GetInstance().Log("Right Thumbstick Up!");
					}
					else if (event.jaxis.value > -12800 && event.jaxis.value <= 0) //if value inside of deadzone
					{
						LogManager::GetInstance().Log("Right Thumbstick Up Released!");
					}

					if (event.jaxis.value > 12800) //if value outside of deadzone
					{
						LogManager::GetInstance().Log("Right Thumbstick Down!");
					}
					else if (event.jaxis.value < 12800 && event.jaxis.value >= 0) //if value inside of deadzone
					{
						LogManager::GetInstance().Log("Right Thumbstick Down Released!");
					}
				}

				if (event.jaxis.axis == 2) //Left Trigger
				{
					if (event.jaxis.value > -32767) //if value outside of deadzone
					{
						LogManager::GetInstance().Log("Left Trigger Pulled!");
					}
					else if (event.jaxis.value == -32767) //if value inside of deadzone
					{
						LogManager::GetInstance().Log("Left Trigger Released!");
					}
				}

				if (event.jaxis.axis == 5) //Right Trigger
				{
					if (event.jaxis.value > -32767) //if value outside of deadzone
					{
						LogManager::GetInstance().Log("Right Trigger Pulled!");
					}
					else if (event.jaxis.value == -32767) //if value inside of deadzone
					{
						LogManager::GetInstance().Log("Right Trigger Released!");
					}
				}
			}
		}

		//CONTROLLER BUTTON TREE
		if (event.type == SDL_JOYBUTTONDOWN) //If controller button pressed
		{
			//Controller Button Code Mapping
			//0 == A
			//1 == B
			//2 == X
			//3 == Y
			//4 == LEFT BUMPER
			//5 == RIGHT BUMPER
			//6 == SELECT
			//7 == START
			//8 == L3
			//9 == R3

			if (event.jbutton.button == 0)
			{
				LogManager::GetInstance().Log("Button A");
			}

			if (event.jbutton.button == 1)
			{
				LogManager::GetInstance().Log("Button B");
			}

			if (event.jbutton.button == 2)
			{
				LogManager::GetInstance().Log("Button X");
			}

			if (event.jbutton.button == 3)
			{
				LogManager::GetInstance().Log("Button Y");
			}

			if (event.jbutton.button == 4)
			{
				LogManager::GetInstance().Log("Left Bumper");
			}

			if (event.jbutton.button == 5)
			{
				LogManager::GetInstance().Log("Right Bumper");
			}

			if (event.jbutton.button == 6)
			{
				LogManager::GetInstance().Log("Button Select");
			}

			if (event.jbutton.button == 7)
			{
				LogManager::GetInstance().Log("Button Start");
				game.Quit();
			}

			if (event.jbutton.button == 8)
			{
				LogManager::GetInstance().Log("Left Stick Click");
			}

			if (event.jbutton.button == 9)
			{
				LogManager::GetInstance().Log("Right Stick Click");
			}

		}
		else if (event.type == SDL_CONTROLLERBUTTONUP) //if controller button released
		{
			//Controller Button Code Mapping
			//0 == A
			//1 == B
			//2 == X
			//3 == Y
			//4 == LEFT BUMPER
			//5 == RIGHT BUMPER
			//6 == SELECT
			//7 == START
			//8 == L3
			//9 == R3

			if (event.jbutton.button == 0)
			{
				LogManager::GetInstance().Log("Button A Released");
			}

			if (event.jbutton.button == 1)
			{
				LogManager::GetInstance().Log("Button B Released");
			}

			if (event.jbutton.button == 2)
			{
				LogManager::GetInstance().Log("Button X Released");
			}

			if (event.jbutton.button == 3)
			{
				LogManager::GetInstance().Log("Button Y Released");
			}

			if (event.jbutton.button == 4)
			{
				LogManager::GetInstance().Log("Left Bumper Released");
			}

			if (event.jbutton.button == 5)
			{
				LogManager::GetInstance().Log("Right Bumper Released");
			}

			if (event.jbutton.button == 6)
			{
				LogManager::GetInstance().Log("Button Select Released");
			}

			if (event.jbutton.button == 7)
			{
				LogManager::GetInstance().Log("Button Start Released");
				game.Quit();
			}

			if (event.jbutton.button == 8)
			{
				LogManager::GetInstance().Log("Left Stick Click Released");
			}

			if (event.jbutton.button == 9)
			{
				LogManager::GetInstance().Log("Right Stick Click Released");
			}
		}

		//CONTROLLER D PAD TREE
		if (event.type == SDL_JOYHATMOTION) //If D-Pad pressed
		{
			//Controller D-Pad Code Mapping
			//0 == Centered
			//1 == Up
			//2 == Right
			//3 == Right Up
			//4 == Down
			//6 == Right Down
			//8 == Left
			//9 == Left Up
			//12 == Left Down

			if (event.jhat.value == 0)
			{
				LogManager::GetInstance().Log("D Pad Centered");
			}

			if (event.jhat.value == 1)
			{
				LogManager::GetInstance().Log("D Pad Up");
			}

			if (event.jhat.value == 2)
			{
				LogManager::GetInstance().Log("D Pad Right");
			}

			if (event.jhat.value == 3)
			{
				LogManager::GetInstance().Log("D Pad Right Up");
			}

			if (event.jhat.value == 4)
			{
				LogManager::GetInstance().Log("D Pad Down");
			}

			if (event.jhat.value == 6)
			{
				LogManager::GetInstance().Log("D Pad Right Down");
			}

			if (event.jhat.value == 8)
			{
				LogManager::GetInstance().Log("D Pad Left");
			}

			if (event.jhat.value == 9)
			{
				LogManager::GetInstance().Log("D Pad Left Up");
			}

			if (event.jhat.value == 12)
			{
				LogManager::GetInstance().Log("D Pad Left Down");
			}
		}
	}
}