import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class MainDeck extends Deck{

	public MainDeck(GameObject creator) {
		super(creator);
		this.deck = putInCards(creator);
	}

	/**
	 * creates the deck
	 * @param args
	 */
	public ArrayList<Card> putInCards(GameObject creator) {
		BufferedImage img;
		try {
			img = ImageIO.read(new File("exampleCardIcon.png"));
			
			ArrayList<String> categories = new ArrayList<String>();
			
			Game g = creator.getGame();
		
			categories.add("fruit");
			categories.add("yellow");
			Possession lemons = new Possession("lemons", img, "lemons", 0, this, categories);
			addCard(lemons);
			categories.clear();
		
			categories.add("fruit");
			categories.add("White");
			Possession cantaloupes = new Possession("cantaloupes", img, "cantaloupes", 1, this, categories);
			addCard(cantaloupes);
			categories.clear();
		
			categories.add("fruit");
			categories.add("red");
			Possession strawberries = new Possession("strawberries", img, "strawberries", 2, this, categories);
			addCard(strawberries);
			categories.clear();
		
			categories.add("fruit");
			categories.add("blue");
			Possession blueberries = new Possession("blueberries", img, "blueberries", 3, this, categories);
			addCard(blueberries);
			categories.clear();
		
			categories.add("fruit");
			categories.add("yellow");
			Possession bananas = new Possession("bananas", img, "bananas", 4, this, categories);
			addCard(bananas);
			categories.clear();
		
			categories.add("fruit");
			categories.add("red");
			Possession apples = new Possession("apples", img, "apples", 5, this, categories);
			addCard(apples);
			categories.clear();
			
			categories.add("fruit");
			categories.add("black");
			Possession figs = new Possession("figs", img, "figs", 6, this, categories);
			addCard(figs);
			categories.clear();
		
			categories.add("fruit");
			categories.add("yellow");
			Possession pears = new Possession("pears", img, "pears", 7, this, categories);
			addCard(pears);
			categories.clear();
		
			categories.add("fruit");
			categories.add("purple");
			Possession grapes = new Possession("grapes", img, "grapes", 8, this, categories);
			addCard(grapes);
			categories.clear();
		
			categories.add("fruit");
			categories.add("orange");
			Possession grapefruits = new Possession("grapefruits", img, "grapefruits", 9, this, categories);
			addCard(grapefruits);
			categories.clear();
		
			categories.add("fruit");
			categories.add("yellow");
			Possession starfruits = new Possession("starfruits", img, "starfruits", 10, this, categories);
			addCard(starfruits);
			categories.clear();
		
			categories.add("fruit");
			categories.add("purple");
			Possession passionfruits = new Possession("passionfruits", img, "passionfruits", 11, this, categories);
			addCard(passionfruits);
			categories.clear();
		
			categories.add("fruit");
			categories.add("orange");
			Possession oranges = new Possession("oranges", img, "oranges", 12, this, categories);
			addCard(oranges);
			categories.clear();
		
			categories.add("fruit");
			categories.add("green");
			Possession watermelons = new Possession("watermelons", img, "watermelons", 13, this, categories);
			addCard(watermelons);
			categories.clear();
		
			categories.add("fruit");
			categories.add("yellow");
			Possession pineapples = new Possession("pineapples", img, "pineapples", 14, this, categories);
			addCard(pineapples);
			categories.clear();
			
			categories.add("fruit");
			categories.add("red");
			Possession raspberries = new Possession("raspberries", img, "raspberries", 15, this, categories);
			addCard(raspberries);
			categories.clear();
			
			categories.add("fruit");
			categories.add("orange");
			Possession tangerines = new Possession("tangerines", img, "tangerines", 16, this, categories);
			addCard(tangerines);
			categories.clear();
			
			categories.add("fruit");
			categories.add("green");
			Possession limes = new Possession("limes", img, "limes", 17, this, categories);
			addCard(limes);
			categories.clear();
			
			categories.add("fruit");
			categories.add("brown");
			Possession coconuts = new Possession("coconuts", img, "coconuts", 18, this, categories);
			addCard(coconuts);
			categories.clear();
			
			categories.add("fruit");
			categories.add("brown");
			Possession cocoaNuts = new Possession("cocoa nuts", img, "cocoa nuts", 19, this, categories);
			addCard(cocoaNuts);
			categories.clear();
			
			categories.add("fruit");
			categories.add("red");
			Possession pomegranates = new Possession("pomegranates", img, "pomegranates", 20, this, categories);
			addCard(pomegranates);
			categories.clear();
			
			categories.add("fruit");
			categories.add("red");
			Possession tomatoes = new Possession("tomatoes", img, "tomatoes", 21, this, categories);
			addCard(tomatoes);
			categories.clear();
			
			categories.add("fruit");
			categories.add("green");
			Possession durians = new Possession("durians", img, "durians", 22, this, categories);
			addCard(durians);
			categories.clear();
			
			categories.add("veggie");
			categories.add("black");
			Possession avocados = new Possession("avocados", img, "avocados", 23, this, categories);
			addCard(avocados);
			categories.clear();
			
			categories.add("veggie");
			categories.add("green");
			Possession cucumbers = new Possession("cucumber", img, "cucumber", 24, this, categories);
			addCard(cucumbers);
			categories.clear();
			
			categories.add("legume");
			categories.add("green");
			Possession limaBeans = new Possession("lima beans", img, "lima beans", 25, this, categories);
			addCard(limaBeans);
			categories.clear();
			
			categories.add("legume");
			categories.add("green");
			Possession snowPeas = new Possession("snowPeas", img, "snowPeas", 26, this, categories);
			addCard(snowPeas);
			categories.clear();
			
			categories.add("legume");
			categories.add("gray");
			Possession peanuts = new Possession("peanuts", img, "peanuts", 27, this, categories);
			addCard(peanuts);
			categories.clear();
			
			categories.add("legume");
			categories.add("white");
			Possession chickpeas = new Possession("chickpeas", img, "chickpeas", 28, this, categories);
			addCard(chickpeas);
			categories.clear();
			
			categories.add("veggie");
			categories.add("orange");
			Possession carrots = new Possession("carrots", img, "carrots", 29, this, categories);
			addCard(carrots);
			categories.clear();
			
			categories.add("veggie");
			categories.add("purple");
			Possession eggplants = new Possession("eggplants", img, "eggplants", 30, this, categories);
			addCard(eggplants);
			categories.clear();
			
			categories.add("pepper");
			categories.add("red");
			Possession jalapenos = new Possession("apples", img, "apples", 31, this, categories);
			addCard(jalapenos);
			categories.clear();
			
			categories.add("pepper");
			categories.add("green");
			Possession chilis = new Possession("chilis", img, "chilis", 32, this, categories);
			addCard(chilis);
		 	categories.clear();
		
		 	categories.add("veggie");
		 	categories.add("yellow");
		 	Possession corns = new Possession("corn", img, "corn", 33, this, categories);
		 	addCard(corns);
		 	categories.clear();
		
		 	categories.add("Squash");
		 	categories.add("green");
		 	Possession acornSquashes = new Possession("acorn squashes", img, "acorn squashes", 34, this, categories);
		 	addCard(acornSquashes);
		 	categories.clear();
		
		 	categories.add("squash");
		 	categories.add("white");
		 	Possession whitePumpkins = new Possession("white pumpkins", img, "white pumpkins", 35, this, categories);
		 	addCard(whitePumpkins);
		 	categories.clear();
		
		 	categories.add("squash");
		 	categories.add("white");
		 	Possession butternutSquashes = new Possession("butternut squashes", img, "butternut squashes", 36, this, categories);
		 	addCard(butternutSquashes);
		 	categories.clear();
		
		 	categories.add("squash");
		 	categories.add("yellow");
		 	Possession spaghettiSquashes = new Possession("spaghetti squashes", img, "spaghetti squashes", 37, this, categories);
		 	addCard(spaghettiSquashes);
		 	categories.clear();
		
		 	categories.add("veggie");
		 	categories.add("green");
		 	Possession broccolis = new Possession("broccolis", img, "brocolis", 38, this, categories);
		 	addCard(broccolis);
		 	categories.clear();
		
		 	categories.add("veggie");
		 	categories.add("green");
		 	Possession spinaches = new Possession("spinaches", img, "spinaches", 39, this, categories);
		 	addCard(spinaches);
		 	categories.clear();
		
		 	categories.add("legumes");
		 	categories.add("white");
		 	Possession beanSprouts = new Possession("bean sprouts", img, "bean sprouts", 40, this, categories);
		 	addCard(beanSprouts);
		 	categories.clear();
		
		 	categories.add("veggie");
		 	categories.add("green");
		 	Possession aspharaguses = new Possession("aspharaguses", img, "aspharaguses", 41, this, categories);
		 	addCard(aspharaguses);
		 	categories.clear();
		
		 	categories.add("legume");
		 	categories.add("green");
		 	Possession edamames = new Possession("edamames", img, "edamames", 42, this, categories);
		 	addCard(edamames);
		 	categories.clear();
		
		 	categories.add("legumes");
		 	categories.add("green");
		 	Possession greenBeans = new Possession("green beans", img, "green beans", 43, this, categories);
		 	addCard(greenBeans);
		 	categories.clear();
		
		 	categories.add("veggie");
		 	categories.add("green");
		 	Possession artichokes = new Possession("artichokes", img, "artichokes", 44, this, categories);
		 	addCard(artichokes);
		 	categories.clear();
		
		 	categories.add("fruit");
		 	categories.add("white");
		 	Possession waterChestnuts = new Possession("water chestnuts", img, "water chestnuts", 45, this, categories);
		 	addCard(waterChestnuts);
		 	categories.clear();
		
		 	categories.add("veggie");
		 	categories.add("red");
		 	Possession radishes = new Possession("radishes", img, "radishes", 46, this, categories);
		 	addCard(radishes);
		 	categories.clear();
		
		 	categories.add("veggie");
		 	categories.add("purple");
		 	Possession cabbages = new Possession("cabbages", img, "the purple kind", 47, this, categories);
		 	addCard(cabbages);
		 	categories.clear();
		
		 	categories.add("veggie");
		 	categories.add("green");
		 	Possession celerys = new Possession("celery stalks", img, "celery stalks", 48, this, categories);
		 	addCard(celerys);
		 	categories.clear();
		
		 	categories.add("veggie");
		 	categories.add("green");
		 	Possession kales = new Possession("kale leaves", img, "kale leaves", 49, this, categories);
		 	addCard(kales);
		 	categories.clear();
		
		 	categories.add("squash");
		 	categories.add("orange");
		 	Possession pumpkins = new Possession("pumpkins", img, "spooky", 50, this, categories);
		 	addCard(pumpkins);
		 	categories.clear();
		
		 	categories.add("legume");
		 	categories.add("green");
		 	Possession peas = new Possession("peas", img, "peas", 51, this, categories);
		 	addCard(peas);
		 	categories.clear();
		
		 	categories.add("veggies");
		 	categories.add("red");
		 	Possession beets = new Possession("beets", img, "check out these funky beets", 52, this, categories);
		 	addCard(beets);
		 	categories.clear();
		
		 	categories.add("veggies");
		 	categories.add("white");
		 	Possession turnips = new Possession("turnips", img, "turnips", 53, this, categories);
		 	addCard(turnips);
		 	categories.clear();
		
		 	categories.add("veggies");
		 	categories.add("white");
		 	Possession cauliflowers = new Possession("cauliflowers", img, "cauliflowers", 54, this, categories);
		 	addCard(cauliflowers);
		 	categories.clear();
		
		 	categories.add("veggies");
		 	categories.add("yellow");
		 	Possession potatoes = new Possession("potatoes", img, "potatoes", 55, this, categories);
		 	addCard(potatoes);
		 	categories.clear();
		
		 	//begin making goals
		 	ArrayList<Possession> winCards = new ArrayList<Possession>();
		
		 	winCards.add(apples);
		 	winCards.add(bananas);
		 	Goal iLikeToOot = new Goal(g, "I like to oot oot opples and baynaynays", img, "apples and bananas", 56, this, (ArrayList<Possession>) winCards.clone());
		 	addCard(iLikeToOot);
		 	winCards.clear();
		
		 	winCards.add(starfruits);
		 	winCards.add(passionfruits);
		 	Goal RnJ = new Goal(g, "Romeo and Juliet", img, "starfruits and passionfruits", 57, this, (ArrayList<Possession>) winCards.clone());
		 	addCard(RnJ);
		 	winCards.clear();
		
		 	winCards.add(cucumbers);
		 	winCards.add(corns);
		 	winCards.add(butternutSquashes);
		 	Goal penis = new Goal(g, "Oddly Phallic", img, "cucumbers and corn and butternut squashes", 58, this, (ArrayList<Possession>) winCards.clone());
		 	addCard(penis);
		 	winCards.clear();
		
		 	winCards.add(jalapenos);
		 	winCards.add(chilis);
		 	Goal spicy = new Goal(g, "Spicy", img, "jalapenos and chilis", 59, this, (ArrayList<Possession>) winCards.clone());
		 	addCard(spicy);
		 	winCards.clear();
		
		 	winCards.add(apples);
		 	winCards.add(grapes);
		 	Goal grapples = new Goal(g, "Grapples", img, "grapes and apples", 60, this, (ArrayList<Possession>) winCards.clone());
		 	addCard(grapples);
		 	winCards.clear();
		
		 	winCards.add(grapefruits);
		 	winCards.add(tangerines);
		 	Goal tangelo = new Goal(g, "tangelo", img, "grapefruits and tangerines", 61, this, (ArrayList<Possession>) winCards.clone());
		 	addCard(tangelo);
		 	winCards.clear();
		
		 	winCards.add(limes);
		 	winCards.add(coconuts);
		 	Goal shake = new Goal(g, "Shake it all up", img, "limes and coconuts", 62, this, (ArrayList<Possession>) winCards.clone());
		 	addCard(shake);
		 	winCards.clear();
		
		 	winCards.add(lemons);
		 	winCards.add(limes);
		 	Goal sour = new Goal(g, "Sourrrr", img, "lemons and limes", 63, this, (ArrayList<Possession>) winCards.clone());
		 	addCard(sour);
		 	winCards.clear();
		
		 	winCards.add(raspberries);
		 	winCards.add(blueberries);
		 	winCards.add(strawberries);
		 	Goal berryBlast = new Goal(g, "berry blast", img, "berry blast", 64, this, (ArrayList<Possession>) winCards.clone());
		 	addCard(berryBlast);
		 	winCards.clear();
		
		 	winCards.add(oranges);
		 	Goal rhyme = new Goal(g, "Nothing rhymes with...", img, "oranges", 65, this, (ArrayList<Possession>) winCards.clone());
		 	addCard(rhyme);
		 	winCards.clear();
		
		 	winCards.add(watermelons);
		 	winCards.add(cantaloupes);
		 	Goal melons = new Goal(g, "Nice melons", img, "watermelons and cantaloupes", 66, this, (ArrayList<Possession>) winCards.clone());
		 	addCard(melons);
		 	winCards.clear();
		
		 	winCards.add(pears);
		 	winCards.add(cantaloupes);
		 	Goal elope = new Goal(g, "the pear cantaloupe", img, "pears and cantaloupes", 67, this, (ArrayList<Possession>) winCards.clone());
		 	addCard(sour);
		 	winCards.clear();
		
		 	winCards.add(coconuts);
		 	winCards.add(cocoaNuts);
		 	Goal cocomo = new Goal(g, "way down in cocomo", img, "coconuts and cocoa nuts", 68, this, (ArrayList<Possession>) winCards.clone());
		 	addCard(cocomo);
		 	winCards.clear();
		
		 	winCards.add(pineapples);
		 	winCards.add(durians);
			Goal pointy = new Goal(g, "Pointy", img, "pineapples and durians", 69, this, (ArrayList<Possession>) winCards.clone());
			addCard(pointy);
			winCards.clear();
		
			winCards.add(figs);
			winCards.add(grapes);
			Goal dried = new Goal(g, "dried", img, "grapes and figs", 70, this, (ArrayList<Possession>) winCards.clone());
			addCard(dried);
			winCards.clear();
		
			winCards.add(tomatoes);
			winCards.add(potatoes);
			winCards.add(eggplants);
			Goal nightshades = new Goal(g, "nightshades", img, "tomatoes and potatoes and eggplants", 71, this, (ArrayList<Possession>) winCards.clone());
			addCard(nightshades);
			winCards.clear();
		
			winCards.add(tomatoes);
			winCards.add(avocados);
			Goal guac = new Goal(g, "guacamole", img, "tomatoes and avocados", 72, this, (ArrayList<Possession>) winCards.clone());
			addCard(guac);
			winCards.clear();
		
			winCards.add(carrots);
			winCards.add(turnips);
			Goal roots = new Goal(g, "get back to your roots", img, "carrots and turnips", 73, this, (ArrayList<Possession>) winCards.clone());
			addCard(roots);
			winCards.clear();
		
			winCards.add(beets);
			winCards.add(radishes);
			Goal roots2 = new Goal(g, "get back to your roots part II", img, "beets and radishes", 74, this, (ArrayList<Possession>) winCards.clone());
			addCard(roots2);
			winCards.clear();
		
			winCards.add(potatoes);
			winCards.add(tomatoes);
			Goal toes = new Goal(g, "toes", img, "tomatoes and potatoes", 75, this, (ArrayList<Possession>) winCards.clone());
			addCard(toes);
			winCards.clear();
		
			winCards.add(pomegranates);
			winCards.add(artichokes);
			Goal heart = new Goal(g, "to the heart of the matter", img, "pomegranates and artichokes", 76, this, (ArrayList<Possession>) winCards.clone());
			addCard(heart);
			winCards.clear();
		
			winCards.add(broccolis);
			winCards.add(cauliflowers);
			Goal diff = new Goal(g, "what's the difference", img, "broccolis and cauliflowers", 77, this, (ArrayList<Possession>) winCards.clone());
			addCard(diff);
			winCards.clear();
		
			winCards.add(cabbages);
			winCards.add(spinaches);
			winCards.add(kales);
			Goal leaf = new Goal(g, "leafy", img, "cabbage and kale and spinach", 78, this, (ArrayList<Possession>) winCards.clone());
			addCard(leaf);
			winCards.clear();
		
			winCards.add(peas);
			winCards.add(chickpeas);
			winCards.add(snowPeas);
			Goal peace = new Goal(g, "give peas a chance", img, "peas and chick peas and snow peas", 79, this, (ArrayList<Possession>) winCards.clone());
			addCard(peace);
			winCards.clear();
		
			winCards.add(waterChestnuts);
			winCards.add(peanuts);
			Goal nuts = new Goal(g, "not nuts", img, "peanuts and water chestnuts", 80, this, (ArrayList<Possession>) winCards.clone());
			addCard(nuts);
			winCards.clear();
		
			winCards.add(celerys);
			winCards.add(aspharaguses);
			Goal stalk = new Goal(g, "stalker", img, "celery and aspharagus", 81, this, (ArrayList<Possession>) winCards.clone());
			addCard(stalk);
			winCards.clear();
		
			winCards.add(pumpkins);
			winCards.add(whitePumpkins);
			Goal spook = new Goal(g, "spooky scary", img, "pumpkins and white pumpkins", 82, this, (ArrayList<Possession>) winCards.clone());
			addCard(spook);
			winCards.clear();
		
			winCards.add(limaBeans);
			winCards.add(greenBeans);
			winCards.add(beanSprouts);
			Goal magic = new Goal(g, "the magical fruit", img, "lima beans and green beans and bean sprouts", 83, this, (ArrayList<Possession>) winCards.clone());
			addCard(magic);
			winCards.clear();
		
			winCards.add(edamames);
			winCards.add(eggplants);
			Goal e = new Goal(g, "EEEEEEEEEEE!", img, "edamames and eggplants", 84, this, (ArrayList<Possession>) winCards.clone());
			addCard(e);
			winCards.clear();
		
			winCards.add(acornSquashes);
			winCards.add(spaghettiSquashes);
			Goal lies = new Goal(g, "LIES!", img, "acorn squash and spaghetti squash", 85, this, (ArrayList<Possession>) winCards.clone());
			addCard(lies);
			winCards.clear();
		
			winCards.add(grapes);
			winCards.add(grapefruits);
			Goal fail = new Goal(g, "I fail to see the resemblance", img, "grapes and grapefruits", 86, this, (ArrayList<Possession>) (ArrayList<Possession>) winCards.clone());
			addCard(fail);
			winCards.clear();
		
			//rule cards
			RuleCard play1 = new RuleCard("Play 1", img, "you may play one card now", 87, this, 1, 1);
			RuleCard play2 = new RuleCard("Play 2", img, "you may play two cards now", 88, this, 1, 2);
			RuleCard play3 = new RuleCard("Play 3", img, "you may play three cards now", 89, this, 1, 3);
			RuleCard play4 = new RuleCard("Play 4", img, "you may play four cards now", 90, this, 1, 4);

			RuleCard draw1 = new RuleCard("Draw 1", img, "you may draw one card now", 91, this, 2, 1);
			RuleCard draw2 = new RuleCard("Draw 2", img, "you may draw two cards now", 92, this, 2, 2);
			RuleCard draw3 = new RuleCard("Draw 3", img, "you may draw three cards now", 93, this, 2, 3);
			RuleCard draw4 = new RuleCard("Draw 4", img, "you may draw four cards now", 94, this, 2, 4);

			RuleCard posLim2 = new RuleCard("possession limit 2", img, "you may have up to two possessions in your pen", 95, this, 3, 2);
			RuleCard posLim3 = new RuleCard("possession limit 3", img, "you may have up to three possessions in your pen", 96, this, 3, 3);
			RuleCard posLim4 = new RuleCard("possession limit 4", img, "you may have up to four possessions in your pen", 97, this, 3, 4);

			RuleCard handLim1 = new RuleCard("hand limit 1", img, "you may have up to one card in your hand", 98, this, 4, 1);
			RuleCard handLim2 = new RuleCard("hand limit 2", img, "you may have up to two cards in your hand", 99, this, 4, 2);
			RuleCard handLim3 = new RuleCard("hand limit 3", img, "you may have up to three cards in your hand", 100, this, 4, 3);
			RuleCard handLim4 = new RuleCard("hand limit 4", img, "you may have up to four cards in your hand", 101, this, 4, 4);
		
			addCard(play1);
			addCard(play2);
			addCard(play3);
			addCard(play4);
			addCard(draw1);
			addCard(draw2);
			addCard(draw3);
			addCard(draw4);
			addCard(posLim2);
			addCard(posLim3);
			addCard(posLim4);
			addCard(handLim1);
			addCard(handLim2);
			addCard(handLim3);
			addCard(handLim4);
			
		} //end try
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
		return deck;
	}

}
