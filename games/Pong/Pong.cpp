#include "Pong.h"

#include <blib/SpriteBatch.h>
#include <blib/ResourceManager.h>
#include <blib/Util.h>
#include <blib/Math.h>

#include "../../PlayAllTheGames/Settings.h"
#include "../../PlayAllTheGames/Participant.h"

#define _USE_MATH_DEFINES
#include <math.h>

#include <glm/gtc/matrix_transform.hpp>

Pong::ball *gameball;
GameBase* getGame()
{
	return new Pong();
}

std::string Pong::getName()
{
	return "Pong";
}

std::string Pong::getInstructions()
{
	return "Reflect the ball";
}

void Pong::loadResources()
{
	backSprite = resourceManager->getResource<blib::Texture>("assets/games/Pong/back.png");
	playerSprite = resourceManager->getResource<blib::Texture>("assets/games/Pong/rectangle.png");
	ballSprite = resourceManager->getResource<blib::Texture>("assets/games/Pong/Ball.png");
	wallSprite = resourceManager->getResource<blib::Texture>("assets/games/Pong/wall.png");
	font = resourceManager->getResource<blib::Font>("menu");
}

void Pong::start(Difficulty difficulty)
{
	positive = false;
	speed = 4;
	turningFactor = 0;
	rotation = rand();
	walls.clear();	

	maxPlayerScore = 1;

	for (auto p : players)
	{
		if (players.size() > 4)
		{
			if (p->index < 4)
			{
				p->position = glm::vec2(960, 540) + 620.0f * blib::util::fromAngle(p->index / (float)4 * 2 * (float)M_PI - (float)0.18*M_PI);
				p->rotation = (float)M_PI / (float)4 * 2 * p->index;
			}
			else
			{
				p->position = glm::vec2(960, 540) + 620.0f * blib::util::fromAngle(p->index / (float)4 * 2 * (float)M_PI + (float)0.18*M_PI);
				p->rotation = (float)M_PI / (float)4 * 2 * p->index;
			}
		}
		if (players.size() < 3)
		{
			p->position = glm::vec2(1920 / 2, 1080 / 2) + 500.0f * blib::util::fromAngle(p->index / (float)2 * 2 * (float)M_PI);
			p->rotation = (float)M_PI / (float)2 * 2 * p->index;
		}
		else if (players.size() < 5)
		{
			p->position = glm::vec2(1920 / 2, 1080 / 2) + 500.0f * blib::util::fromAngle(p->index / (float)4 * 2 * (float)M_PI);
			p->rotation = (float)M_PI / (float)4 * 2 * p->index;
		}
	}
	for (int i = 0; i < 4; i++)
	{
		glm::vec2 v = glm::vec2(1920 / 2, 1080 / 2) + 540.0f * blib::util::fromAngle(i / (float)4 * 2 * (float)M_PI);
		walls.push_back(v);
	}
	gameball = new Pong::ball(1920 / 2, 1080 / 2, 25);
}

void Pong::update(float elapsedTime)
{
	blib::math::Rectangle screenRect(0, 0, 1920, 1080);
	int PlayersDefeated = 0;
	for (auto p : players)
	{
		if (p->joystick.leftStick.y < 0 && ((players.size() < 3) || (players.size() < 9 && p->index % 2 == 0)))
		{
			glm::vec2 oldPosition = p->position;
			p->position -= 10.0f * blib::util::fromAngle(M_PI*0.5) * 60.0f*elapsedTime;
			bool collision = false;
			for (auto pp : players)
			{
				if (p == pp)
					continue;
				if (glm::length(pp->position - p->position) < 100)
				{
					collision = true;
					pp->rotation += (float)M_PI;
				}
			}
			if (!screenRect.contains(p->position))
				collision = true;

			if (collision)
			{
				p->position = oldPosition;
				p->rotation += (float)M_PI;
			}
		}
		if (p->joystick.leftStick.y > 0 && ((players.size() < 3) || (players.size() < 9 && (p->index % 2 == 0))))
		{
			glm::vec2 oldPosition = p->position;
			p->position += 10.0f * blib::util::fromAngle(M_PI*0.5) * 60.0f*elapsedTime;
			bool collision = false;
			for (auto pp : players)
			{
				if (p == pp)
					continue;
				if (glm::length(pp->position - p->position) < 100)
				{
					collision = true;
					pp->rotation += (float)M_PI;
				}
			}
			if (!screenRect.contains(p->position))
				collision = true;

			if (collision)
			{
				p->position = oldPosition;
				p->rotation += (float)M_PI;
			}
		}
		if (p->joystick.leftStick.x < 0 && (players.size() < 9 && p->index % 2 == 1))
		{
			glm::vec2 oldPosition = p->position;
			p->position -= 10.0f * blib::util::fromAngle(0) * 60.0f*elapsedTime;
			bool collision = false;
			for (auto pp : players)
			{
				if (p == pp)
					continue;
				if (glm::length(pp->position - p->position) < 100)
				{
					collision = true;
					pp->rotation += (float)M_PI;
				}
			}
			if (!screenRect.contains(p->position))
				collision = true;

			if (collision)
			{
				p->position = oldPosition;
				p->rotation += (float)M_PI;
			}
		}
		if (p->joystick.leftStick.x > 0 && (players.size() < 9 && p-> index % 2 == 1))
		{
			glm::vec2 oldPosition = p->position;
			p->position += 10.0f * blib::util::fromAngle(0) * 60.0f*elapsedTime;
			bool collision = false;
			for (auto pp : players)
			{
				if (p == pp)
					continue;
				if (glm::length(pp->position - p->position) < 100)
				{
					collision = true;
					pp->rotation += (float)M_PI;
				}
			}
			if (!screenRect.contains(p->position))
				collision = true;

			if (collision)
			{
				p->position = oldPosition;
				p->rotation += (float)M_PI;
			}
		}
		
		glm::vec2 suckPos = p->position + 50.0f*blib::util::fromAngle(p->rotation);
		
		if (checkCollision(*p) && p->score != -1)
		{
			speed += 1;
			float rotationdiff = (p->rotation + 0.5*M_PI) - rotation;
			rotation += 2 * rotationdiff;
		}
		if (p->score == -1)
		{
			PlayersDefeated++;
		}
	}
	if (!screenRect.contains(gameball->coordinates[0]))
	{
		float distance = 10000;
		int playerindex = 0;
		for (auto p : players)
		{
			if (calculateDistance(gameball->coordinates[0], p->position) < distance)
			{
				distance = calculateDistance(gameball->coordinates[0], p->position);
				playerindex = p->index;
			}
		}
		players[playerindex]->score = -1;
		players[playerindex]->position = glm::vec2(-1000, -1000);
		gameball->coordinates[0] = glm::vec2(1920 / 2, 1080 / 2);
		speed = 4;
		rotation = rand();
	}
	for (int i = 0; i < players.size(); i++)
	{
		if ((players[i]->score < 0 && i < 4 && players.size() < 5) || (players.size() > 4 && i + 4 >= players.size() && players[i]->score < 0 && i < 4))
		{
			if (i == 0 && gameball->coordinates[0].x > 1420)
			{
				printf("something");
				speed += 0.5;
				float rotationdiff = (players[i]->rotation + 0.5*M_PI) - rotation;
				rotation += 2 * rotationdiff;
			}
			if (i == 1 && gameball->coordinates[0].y > 1005)
			{
				printf("something");
				speed += 0.5;
				float rotationdiff = (players[i]->rotation + 0.5*M_PI) - rotation;
				rotation += 2 * rotationdiff;
			}
			if (i == 2 && gameball->coordinates[0].x < 495)
			{
				printf("something");
				speed += 0.5;
				float rotationdiff = (players[i]->rotation + 0.5*M_PI) - rotation;
				rotation += 2 * rotationdiff;
			}
			if (i == 3 && gameball->coordinates[0].y < 75)
			{
				printf("something");
				speed += 0.5;
				float rotationdiff = (players[i]->rotation + 0.5*M_PI) - rotation;
				rotation += 2 * rotationdiff;
			}
		}
		if (players[i]->score < 0 && i > 3)
		{
			if (players[i - 4]->score < 0)
			{
				if (i == 4 && gameball->coordinates[0].x > 1420)
				{
					printf("something");
					speed += 0.5;
					float rotationdiff = (players[i]->rotation + 0.5*M_PI) - rotation;
					rotation += 2 * rotationdiff;
				}
				if (i == 5 && gameball->coordinates[0].y > 1005)
				{
					printf("something");
					speed += 0.5;
					float rotationdiff = (players[i]->rotation + 0.5*M_PI) - rotation;
					rotation += 2 * rotationdiff;
				}
				if (i == 6 && gameball->coordinates[0].x < 495)
				{
					printf("something");
					speed += 0.5;
					float rotationdiff = (players[i]->rotation + 0.5*M_PI) - rotation;
					rotation += 2 * rotationdiff;
				}
				if (i == 7 && gameball->coordinates[0].y < 75)
				{
					printf("something");
					speed += 0.5;
					float rotationdiff = (players[i]->rotation + 0.5*M_PI) - rotation;
					rotation += 2 * rotationdiff;
				}
			}
		}
	}
	if (players.size() < 3)
	{
		if (gameball->coordinates[0].y < 75)
		{
			speed += 0.5;
			double rotationdiff = (0) - rotation;; // moet nog gefixt worden
			rotation += 2 * rotationdiff;

		}
		if (gameball->coordinates[0].y > 1005)
		{
			speed += 0.5;
			double rotationdiff = (0) - rotation;
			rotation += 2 * rotationdiff;
		}
	}
	else if (players.size() < 4)
	{
		if (gameball->coordinates[0].y < 75)
		{
			speed += 0.5;
			float rotationdiff = (0) - rotation; // moet nog gefixt worden
			rotation += 2 * rotationdiff;
		}
	}
	if (PlayersDefeated == players.size()-1)
	{
		for (auto p : players)
		{
			if (p->score == 0)
			{
				p->score = 1;
			}
		}
	}
	gameball->coordinates[0] += speed * 80.0f * blib::util::fromAngle(rotation) * elapsedTime;
}


void Pong::draw()
{
	spriteBatch->begin(settings->scaleMatrix);
	spriteBatch->draw(backSprite, glm::mat4());

	for (auto t : gameball->coordinates) { spriteBatch->draw(ballSprite, blib::math::easyMatrix(t), ballSprite->center); };

	for (auto p : players)
	{
		if ((p->score < 0 && p->index < 4 && players.size() < 5) || (players.size() > 4 && p->index + 4 >= players.size() && p->score < 0 && p->index < 4))
		{
			spriteBatch->draw(wallSprite, glm::rotate(glm::translate(glm::mat4(), glm::vec3(walls[p->index], 0)), glm::degrees(p->rotation), glm::vec3(0, 0, 1)), wallSprite->center, blib::math::Rectangle(0, 0, 1, 1), p->participant->color);
		}
		if (p->score < 0 && p->index > 3)
		{
			if (players[p->index - 4]->score < 0)
			{
				spriteBatch->draw(wallSprite, glm::rotate(glm::translate(glm::mat4(), glm::vec3(walls[p->index-4], 0)), glm::degrees(p->rotation), glm::vec3(0, 0, 1)), wallSprite->center, blib::math::Rectangle(0, 0, 1, 1), players[p->index-4]->participant->color);
			}
		}
		else if (p-> score == 0)
		{
			spriteBatch->draw(playerSprite, glm::rotate(glm::translate(glm::mat4(), glm::vec3(p->position, 0)), glm::degrees(p->rotation), glm::vec3(0, 0, 1)), playerSprite->center, blib::math::Rectangle(0, 0, 1, 1), p->participant->color);
		}
	}
	if (players.size() < 3)
	{
		spriteBatch->draw(wallSprite, glm::rotate(glm::translate(glm::mat4(), glm::vec3(walls[1], 0)), glm::degrees((float)M_PI/2), glm::vec3(0, 0, 1)), wallSprite->center, blib::math::Rectangle(0, 0, 1, 1), players[0]->participant->color);
	}
	if (players.size() < 4)
	{
		spriteBatch->draw(wallSprite, glm::rotate(glm::translate(glm::mat4(), glm::vec3(walls[3], 0)), glm::degrees((float)M_PI/2), glm::vec3(0, 0, 1)), wallSprite->center, blib::math::Rectangle(0, 0, 1, 1), players[1]->participant->color);
	}
	spriteBatch->end();
}

float Pong::calculateDistance(glm::vec2 point1, glm::vec2 point2)
{
	float temp1 = pow(point1.x - point2.x, 2);
	float temp2 = pow(point1.y - point2.y, 2);
	return sqrt(temp1 + temp2);
}

float Pong::calculateAngle(glm::vec2 vector1, glm::vec2 vector2, glm::vec2 vector0) // function calulates angel between 2 lines were vector0 is your origen 0,0 x,y 
{
	vector1.x -= vector0.x;
	vector2.x -= vector0.x;
	vector1.y -= vector0.y;
	vector2.y -= vector0.y;

	float dot = vector1.x*vector2.x + vector1.y*vector2.y;      // dot product
	float det = vector1.x*vector2.y - vector1.y*vector2.x;      // determinant
	float angle = atan2(det, dot);   //atan2(y, x) or atan2(sin, cos)
	return angle;
}

glm::vec2 Pong::rotatePoint(glm::vec2 rotatePoint, float angle, glm::vec2 point)
{
	float s = sin(angle);
	float c = cos(angle);

	// translate point back to origin:
	point.x -= rotatePoint.x;
	point.y -= rotatePoint.y;

	// rotate point
	float xnew = point.x * c - point.y * s;
	float ynew = point.x * s + point.y * c;

	// translate point back:
	

	return glm::vec2(xnew + rotatePoint.x, ynew + rotatePoint.y);


}

bool Pong::checkCollision(PongPlayer player)
{

	glm::vec2 p1 = glm::vec2(player.position.x-25,player.position.y-100);
	glm::vec2 p4 = glm::vec2(player.position.x + 25, player.position.y - 100);
	glm::vec2 p3 = glm::vec2(player.position.x + 25, player.position.y + 100);
	glm::vec2 p2 = glm::vec2(player.position.x - 25, player.position.y + 100);

	float rotation = player.rotation;
	
	p1 = rotatePoint(player.position, rotation, p1);
	p2 = rotatePoint(player.position, rotation, p2);
	p3 = rotatePoint(player.position, rotation, p3);
	p4 = rotatePoint(player.position, rotation, p4);

	//ball radius 25
	
		for (int c = 0; c < 2; c++)
		{
			if (c == 1){ p1 = p3; p2 = p4; }
			int temp = 0;
			if (abs(p1.x - p2.x) > abs(p1.y - p2.y))
			{
				if ((p2.x - p1.x) > 0)
				{
					float xTotal = (p1.x - p2.x);
					int yToX = 0;
					if ((p1.y - p2.y) != 0){ yToX = xTotal / (p1.y - p2.y); }
					int translation = p1.y - (p1.x *yToX);
					for (int i = p1.x; i < p2.x; i++)
					{
						temp = i*yToX + translation;
						if (calculateDistance(glm::vec2(i, temp), gameball->coordinates[0]) <= gameball->Radius)
						{
							return true;
						}
					}
				}
				else
				{
					float xTotal = (p2.x - p1.x);
					int yToX = 0;
					if ((p2.y - p1.y) != 0){ yToX = xTotal / (p2.y - p1.y); }
					int translation = p2.y - (p2.x *yToX);
					for (int i = p2.x; i < p1.x; i++)
					{
						temp = i*yToX + translation;
						if (calculateDistance(glm::vec2(i, temp), gameball->coordinates[0]) <= gameball->Radius)
						{
							return true;
						}
					}
				}
			}
			else
			{
				if ((p2.y - p1.y) > 0)
				{
					float yTotal = (p1.y - p2.y);
					int xToY = 0;
					if ((p1.x - p2.x) != 0){ xToY = yTotal / (p1.x - p2.x); }
					int translation = p1.x - (p1.y *xToY);
					for (int i = p1.y; i < p2.y; i++)
					{
						temp = i*xToY + translation;
						if (calculateDistance(glm::vec2(temp, i), gameball->coordinates[0]) <= gameball->Radius)
						{
							return true;
						}
					}
				}
				else
				{
					float yTotal = (p1.y - p2.y);
					int xToY = 0;
					if ((p2.x - p1.x) != 0){ xToY = yTotal / (p2.x - p1.x); }
					int translation = p2.x - (p2.y *xToY);
					for (int i = p2.y; i < p1.y; i++)
					{
						temp = i*xToY + translation;
						if (calculateDistance(glm::vec2(temp, i), gameball->coordinates[0]) <= gameball->Radius)
						{
							return true;
						}
					}
				}
			}
		}
		return false;
}
		







blib::Texture* Pong::getTitleImage()
{
	return NULL;
}
