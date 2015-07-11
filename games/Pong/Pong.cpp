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
	ball.clear();
	rotation = rand();

	maxPlayerScore = 5;

	for (auto p : players)
	{
		p->position = glm::vec2(1920 / 2, 1080 / 2) + 500.0f * blib::util::fromAngle(p->index / (float)players.size() * 2 * (float)M_PI);
		p->rotation = (float)M_PI / (float)players.size()*2 * turningFactor;
		turningFactor++;
	}
	glm::vec2 v(1920/2, 1080/2);
	ball.push_back(v);
}

void Pong::update(float elapsedTime)
{
	blib::math::Rectangle screenRect(0, 0, 1920, 1080);
	for (auto p : players)
	{
		if (!screenRect.contains(ball[0]))
		{
			p->score += 1;
			ball[0] = glm::vec2(1920 / 2, 1080 / 2);
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
		
		if (blib::linq::any(ball, [p](glm::vec2 t) { return glm::length(t - p->position) < 64; }))
		{
			speed += 0.5;
			rotation -= 1;
		}
		if (checkCollision(p) > 1)
		{
			ball[0].x -= 20 * elapsedTime;
		}
		else
		{
			ball[0] += speed * 20.0f * blib::util::fromAngle(rotation) * elapsedTime;
		}
	}
}


void Pong::draw()
{
	spriteBatch->begin(settings->scaleMatrix);
	spriteBatch->draw(backSprite, glm::mat4());

	for (auto t : ball) { spriteBatch->draw(ballSprite, blib::math::easyMatrix(t), ballSprite->center); };
	for (auto p : players) { spriteBatch->draw(playerSprite, glm::rotate(glm::translate(glm::mat4(), glm::vec3(p->position, 0)), glm::degrees(p->rotation), glm::vec3(0, 0, 1)), playerSprite->center, blib::math::Rectangle(0, 0, 1, 1), p->participant->color); }
	for (size_t i = 0; i < players.size(); i++)
		spriteBatch->draw(font, blib::util::toString(players[i]->score), blib::math::easyMatrix(glm::vec2(10, 64 * i), 0, 1), players[i]->participant->color);

	spriteBatch->end();
}

float Pong::checkCollision(PongPlayer player)
{
	float angle1 = 2 * M_PI;
	float angle2 = angle1;
	glm::vec2 p1 = glm::vec2(player.position.x-25,player.position.y-100);
	glm::vec2 p2 = glm::vec2(player.position.x + 25, player.position.y - 100);
	glm::vec2 p3 = glm::vec2(player.position.x + 25, player.position.y + 100);
	glm::vec2 p4 = glm::vec2(player.position.x - 25, player.position.y + 100);
	
	rotatePoint(player.position, player.rotation, p1);
	rotatePoint(player.position, player.rotation, p2);
	rotatePoint(player.position, player.rotation, p3);
	rotatePoint(player.position, player.rotation, p4);


	//width 50 and heigth 200
	
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

glm::vec2 rotatePoint(glm::vec2 rotatePoint, float angle, glm::vec2 point)
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
	point.x = xnew + cx;
	point.y = ynew + cy;
	return point;
}

blib::Texture* Pong::getTitleImage()
{
	return NULL;
}
