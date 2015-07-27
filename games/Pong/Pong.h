#pragma once

#include "../../PlayAllTheGames/ScoreGame.h"

#include "PongPlayer.h"

namespace blib { class Texture; class Font; }

class Pong : public ScoreGame<PongPlayer>
{
	blib::Texture* backSprite;
	blib::Texture* ballSprite;
	blib::Texture* playerSprite;
	blib::Texture* wallSprite;
	blib::Font* font;

	boolean positive;
	double rotation;
	int turningFactor;
	float speed;
	std::vector<glm::vec2> walls;

public:
	virtual std::pair<int, int> getPlayerCount() { return std::pair<int, int>(1, 100); }
	virtual std::string getName();
	virtual std::string getInstructions();
	virtual void loadResources();
	virtual void start(Difficulty difficulty);
	virtual void update(float elapsedTime);
	virtual void draw();
	virtual blib::Texture* getTitleImage();
	struct ball{
		std::vector<glm::vec2> coordinates;
		float Radius;
		ball(float x, float y, float radius)
		{
			glm::vec2 v(x, y);
			coordinates.push_back(v);
			Radius = radius;
		}
	};

	float calculateDistance(glm::vec2 point1, glm::vec2 point2);
	bool checkCollision(PongPlayer player);
	float calculateAngle(glm::vec2 vector1, glm::vec2 vector2, glm::vec2 vector0);
	glm::vec2 rotatePoint(glm::vec2 rotatePoint, float angle, glm::vec2 point);
	


};







extern "C" {
	__declspec(dllexport) GameBase* getGame();
}