#pragma once

#include "../../PlayAllTheGames/ScoreGame.h"

#include "PongPlayer.h"

namespace blib { class Texture; class Font; }

class Pong : public ScoreGame<PongPlayer>
{
	blib::Texture* backSprite;
	blib::Texture* ballSprite;
	blib::Texture* playerSprite;
	blib::Font* font;

	boolean positive;
	double rotation;
	int turningFactor;
	float speed;

	std::vector<glm::vec2> ball;

public:
	virtual std::pair<int, int> getPlayerCount() { return std::pair<int, int>(1, 100); }
	virtual std::string getName();
	virtual std::string getInstructions();
	virtual void loadResources();
	virtual void start(Difficulty difficulty);
	virtual void update(float elapsedTime);
	virtual void draw();
	virtual blib::Texture* getTitleImage();

private:
	float checkCollision(PongPlayer player);
	float calculateAngle(glm::vec2 vector1, glm::vec2 vector2, glm::vec2 vector0);
	glm::vec2 rotatePoint(glm::vec2 rotatePoint,float angle,glm::vec2 point);
};







extern "C" {
	__declspec(dllexport) GameBase* getGame();
}