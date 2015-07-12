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
	font = resourceManager->getResource<blib::Font>("menu");
}

void Pong::start(Difficulty difficulty)
{
	positive = false;
	speed = 2;
	turningFactor = 0;
	//ball.clear();
	rotation = rand();

	maxPlayerScore = 5;

	for (auto p : players)
	{
		p->position = glm::vec2(1920 / 2, 1080 / 2) + 500.0f * blib::util::fromAngle(p->index / (float)players.size() * 2 * (float)M_PI);
		p->rotation = (float)M_PI / (float)players.size()*2 * turningFactor;
		turningFactor++;
	}
	//glm::vec2 v(1920/2, 1080/2);
	//ball.push_back(v);
	gameball = new Pong::ball(1920 / 2, 1080 / 2, 25);
}

void Pong::update(float elapsedTime)
{
	blib::math::Rectangle screenRect(0, 0, 1920, 1080);
	for (auto p : players)
	{
		if (!screenRect.contains(gameball->coordinates[0]))
		{
			p->score += 1;
			gameball->coordinates[0] = glm::vec2(1920 / 2, 1080 / 2);
			speed = 2;
			rotation = rand();
		}
		if (p->joystick.leftStick.y < 0)
		{
			glm::vec2 oldPosition = p->position;
			p->position += 5.0f * blib::util::fromAngle(300) * 60.0f*elapsedTime;
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
		if (p->joystick.leftStick.y > 0)
		{
			glm::vec2 oldPosition = p->position;
			p->position += 5.0f * blib::util::fromAngle(900) * 60.0f*elapsedTime;
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
		
	/*	if (blib::linq::any(gameball->coordinates, [p](glm::vec2 t) { return glm::length(t - p->position) < 64; }))
		{
			speed += 0.5;
			rotation -= 1;
		}*/
		if (checkCollision(*p))
		{
			//gameball->coordinates[0].x -= 20 * elapsedTime;
			speed += 0.5;
			rotation -= 1;
		}
		else
		{
			gameball->coordinates[0] += speed * 20.0f * blib::util::fromAngle(rotation) * elapsedTime;
		}
	}
}


void Pong::draw()
{
	spriteBatch->begin(settings->scaleMatrix);
	spriteBatch->draw(backSprite, glm::mat4());

	for (auto t : gameball->coordinates) { spriteBatch->draw(ballSprite, blib::math::easyMatrix(t), ballSprite->center); };
	for (auto p : players) { spriteBatch->draw(playerSprite, glm::rotate(glm::translate(glm::mat4(), glm::vec3(p->position, 0)), glm::degrees(p->rotation), glm::vec3(0, 0, 1)), playerSprite->center, blib::math::Rectangle(0, 0, 1, 1), p->participant->color); }
	for (size_t i = 0; i < players.size(); i++)
		spriteBatch->draw(font, blib::util::toString(players[i]->score), blib::math::easyMatrix(glm::vec2(10, 64 * i), 0, 1), players[i]->participant->color);

	spriteBatch->end();
}

float calculateDistance(glm::vec2 point1, glm::vec2 point2)
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

void Pong::rotatePoint(glm::vec2 rotatePoint, float angle, glm::vec2 point)
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
	point.x = xnew + rotatePoint.x;
	point.y = ynew + rotatePoint.y;

}

bool Pong::checkCollision(PongPlayer player)
{

	glm::vec2 p1 = glm::vec2(player.position.x-25,player.position.y-100);
	glm::vec2 p4 = glm::vec2(player.position.x + 25, player.position.y - 100);
	glm::vec2 p3 = glm::vec2(player.position.x + 25, player.position.y + 100);
	glm::vec2 p2 = glm::vec2(player.position.x - 25, player.position.y + 100);

	float rotation = player.rotation;
	
	rotatePoint(player.position, rotation, p1);
	rotatePoint(player.position, rotation, p2);
	rotatePoint(player.position, rotation, p3);
	rotatePoint(player.position, rotation, p4);

	//ball radius 25
	
		for (int c = 0; c < 2; c++)
		{
			if (c == 1){ p1 = p3; p2 = p4; }
			int temp = 0;
			if (abs(p1.x - p2.x) > abs(p1.y - p2.y))
			{
				if ((p1.x - p2.x) > 0)
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
				if ((p1.y - p2.y) > 0)
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
