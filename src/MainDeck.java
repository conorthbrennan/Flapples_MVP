import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class MainDeck extends Deck{

	/**
	 * the constructor for the main deck
	 * @param creator
	 */
	public MainDeck(GameObject creator) {
		super(creator);
		this.deck = putInCards(creator);//creates all the cards for the deck
	}//end constructor

	/**
	 * creates the deck
	 * @param args
	 */
	public ArrayList<Card> putInCards(GameObject creator) {
		BufferedImage goalImg;
		BufferedImage ruleImg;
		try {
			goalImg = ImageIO.read(new File("16947.png"));//the image for goals
			ruleImg = ImageIO.read(new File("obey.png"));//the image for rules
			ArrayList<String> categories = new ArrayList<String>();
			ArrayList<BufferedImage> ico = new ArrayList<BufferedImage>();//the list of icons
			
			Game g = creator.getGame();//sets the game
		

			ico.add(ImageIO.read(new File("pics/lemon.png")));	//the picture
			categories.add("fruit");
			categories.add("yellow");
			Possession lemons = new Possession("lemons", ico.get(ico.size()-1), "lemons", 0, this, categories);//makes the card
			addCard(lemons);//adds it to the deck
			categories.clear();
		
			ico.add(ImageIO.read(new File("pics/cantaloupe.png")));
			categories.add("fruit");
			categories.add("White");
			Possession cantaloupes = new Possession("cantaloupes", ico.get(ico.size()-1), "cantaloupes", 1, this, categories);
			addCard(cantaloupes);
			categories.clear();
		
			ico.add(ImageIO.read(new File("pics/strawberry.png")));
			categories.add("fruit");
			categories.add("red");
			Possession strawberries = new Possession("strawberries", ico.get(ico.size()-1), "strawberries", 2, this, categories);
			addCard(strawberries);
			categories.clear();
		
			ico.add(ImageIO.read(new File("pics/blueberry.png")));
			categories.add("fruit");
			categories.add("blue");
			Possession blueberries = new Possession("blueberries", ico.get(ico.size()-1), "blueberries", 3, this, categories);
			addCard(blueberries);
			categories.clear();
		
			ico.add(ImageIO.read(new File("pics/banana.png")));
			categories.add("fruit");
			categories.add("yellow");
			Possession bananas = new Possession("bananas", ico.get(ico.size()-1), "bananas", 4, this, categories);
			addCard(bananas);
			categories.clear();
		
			ico.add(ImageIO.read(new File("pics/apple.png")));
			categories.add("fruit");
			categories.add("red");
			Possession apples = new Possession("apples", ico.get(ico.size()-1), "apples", 5, this, categories);
			addCard(apples);
			categories.clear();
			
			ico.add(ImageIO.read(new File("pics/fig.png")));
			categories.add("fruit");
			categories.add("black");
			Possession figs = new Possession("figs", ico.get(ico.size()-1), "figs", 6, this, categories);
			addCard(figs);
			categories.clear();
		
			ico.add(ImageIO.read(new File("pics/pear.png")));
			categories.add("fruit");
			categories.add("yellow");
			Possession pears = new Possession("pears", ico.get(ico.size()-1), "pears", 7, this, categories);
			addCard(pears);
			categories.clear();
		
			ico.add(ImageIO.read(new File("pics/grape.png")));
			categories.add("fruit");
			categories.add("purple");
			Possession grapes = new Possession("grapes", ico.get(ico.size()-1), "grapes", 8, this, categories);
			addCard(grapes);
			categories.clear();
		
			ico.add(ImageIO.read(new File("pics/grapefruit.png")));
			categories.add("fruit");
			categories.add("orange");
			Possession grapefruits = new Possession("grapefruits", ico.get(ico.size()-1), "grapefruits", 9, this, categories);
			addCard(grapefruits);
			categories.clear();
		
			ico.add(ImageIO.read(new File("pics/starfruit.png")));
			categories.add("fruit");
			categories.add("yellow");
			Possession starfruits = new Possession("starfruits", ico.get(ico.size()-1), "starfruits", 10, this, categories);
			addCard(starfruits);
			categories.clear();
		
			ico.add(ImageIO.read(new File("pics/passionfruit.png")));
			categories.add("fruit");
			categories.add("purple");
			Possession passionfruits = new Possession("passionfruits", ico.get(ico.size()-1), "passionfruits", 11, this, categories);
			addCard(passionfruits);
			categories.clear();
		
			ico.add(ImageIO.read(new File("pics/orange.png")));
			categories.add("fruit");
			categories.add("orange");
			Possession oranges = new Possession("oranges", ico.get(ico.size()-1), "oranges", 12, this, categories);
			addCard(oranges);
			categories.clear();
		
			ico.add(ImageIO.read(new File("pics/watermelon.png")));
			categories.add("fruit");
			categories.add("green");
			Possession watermelons = new Possession("watermelons", ico.get(ico.size()-1), "watermelons", 13, this, categories);
			addCard(watermelons);
			categories.clear();
		
			ico.add(ImageIO.read(new File("pics/pineapple.png")));
			categories.add("fruit");
			categories.add("yellow");
			Possession pineapples = new Possession("pineapples", ico.get(ico.size()-1), "pineapples", 14, this, categories);
			addCard(pineapples);
			categories.clear();
			
			ico.add(ImageIO.read(new File("pics/raspberry.png")));
			categories.add("fruit");
			categories.add("red");
			Possession raspberries = new Possession("raspberries", ico.get(ico.size()-1), "raspberries", 15, this, categories);
			addCard(raspberries);
			categories.clear();
			
			ico.add(ImageIO.read(new File("pics/tangerine.png")));
			categories.add("fruit");
			categories.add("orange");
			Possession tangerines = new Possession("tangerines", ico.get(ico.size()-1), "tangerines", 16, this, categories);
			addCard(tangerines);
			categories.clear();
			
			ico.add(ImageIO.read(new File("pics/lime.png")));
			categories.add("fruit");
			categories.add("green");
			Possession limes = new Possession("limes", ico.get(ico.size()-1), "limes", 17, this, categories);
			addCard(limes);
			categories.clear();
			
			ico.add(ImageIO.read(new File("pics/coconut.png")));
			categories.add("fruit");
			categories.add("brown");
			Possession coconuts = new Possession("coconuts", ico.get(ico.size()-1), "coconuts", 18, this, categories);
			addCard(coconuts);
			categories.clear();
			
			ico.add(ImageIO.read(new File("pics/cocoa_beans.png")));
			categories.add("fruit");
			categories.add("brown");
			Possession cocoaNuts = new Possession("cocoa nuts", ico.get(ico.size()-1), "cocoa nuts", 19, this, categories);
			addCard(cocoaNuts);
			categories.clear();
			
			ico.add(ImageIO.read(new File("pics/pomegranate.png")));
			categories.add("fruit");
			categories.add("red");
			Possession pomegranates = new Possession("pomegranates", ico.get(ico.size()-1), "pomegranates", 20, this, categories);
			addCard(pomegranates);
			categories.clear();
			
			ico.add(ImageIO.read(new File("pics/tomato.png")));
			categories.add("fruit");
			categories.add("red");
			Possession tomatoes = new Possession("tomatoes", ico.get(ico.size()-1), "tomatoes", 21, this, categories);
			addCard(tomatoes);
			categories.clear();
			
			ico.add(ImageIO.read(new File("pics/durian_yes.png")));
			categories.add("fruit");
			categories.add("green");
			Possession durians = new Possession("durians", ico.get(ico.size()-1), "durians", 22, this, categories);
			addCard(durians);
			categories.clear();
			
			ico.add(ImageIO.read(new File("pics/avocado.png")));
			categories.add("veggie");
			categories.add("black");
			Possession avocados = new Possession("avocados", ico.get(ico.size()-1), "avocados", 23, this, categories);
			addCard(avocados);
			categories.clear();
			
			ico.add(ImageIO.read(new File("pics/cucumber.png")));
			categories.add("veggie");
			categories.add("green");
			Possession cucumbers = new Possession("cucumber", ico.get(ico.size()-1), "cucumber", 24, this, categories);
			addCard(cucumbers);
			categories.clear();
			
			ico.add(ImageIO.read(new File("pics/lima_bean.png")));
			categories.add("legume");
			categories.add("green");
			Possession limaBeans = new Possession("lima beans", ico.get(ico.size()-1), "lima beans", 25, this, categories);
			addCard(limaBeans);
			categories.clear();
			
			ico.add(ImageIO.read(new File("pics/snow_peas.png")));
			categories.add("legume");
			categories.add("green");
			Possession snowPeas = new Possession("snowPeas", ico.get(ico.size()-1), "snowPeas", 26, this, categories);
			addCard(snowPeas);
			categories.clear();
			
			ico.add(ImageIO.read(new File("pics/peanut.png")));
			categories.add("legume");
			categories.add("gray");
			Possession peanuts = new Possession("peanuts", ico.get(ico.size()-1), "peanuts", 27, this, categories);
			addCard(peanuts);
			categories.clear();
			
			ico.add(ImageIO.read(new File("pics/chickpeas.png")));
			categories.add("legume");
			categories.add("white");
			Possession chickpeas = new Possession("chickpeas", ico.get(ico.size()-1), "chickpeas", 28, this, categories);
			addCard(chickpeas);
			categories.clear();
			
			ico.add(ImageIO.read(new File("pics/carrot.png")));
			categories.add("veggie");
			categories.add("orange");
			Possession carrots = new Possession("carrots", ico.get(ico.size()-1), "carrots", 29, this, categories);
			addCard(carrots);
			categories.clear();
			
			ico.add(ImageIO.read(new File("pics/eggplant.png")));
			categories.add("veggie");
			categories.add("purple");
			Possession eggplants = new Possession("eggplants", ico.get(ico.size()-1), "eggplants", 30, this, categories);
			addCard(eggplants);
			categories.clear();
			
			ico.add(ImageIO.read(new File("pics/chili_pepper.png")));
			categories.add("pepper");
			categories.add("red");
			Possession jalapenos = new Possession("jalapenos", ico.get(ico.size()-1), "jalapenos", 31, this, categories);
			addCard(jalapenos);
			categories.clear();
			
			ico.add(ImageIO.read(new File("pics/chili_pepper.png")));
			categories.add("pepper");
			categories.add("green");
			Possession chilis = new Possession("chilis", ico.get(ico.size()-1), "chilis", 32, this, categories);
			addCard(chilis);
		 	categories.clear();
		
		 	ico.add(ImageIO.read(new File("pics/corn.png")));
			categories.add("veggie");
		 	categories.add("yellow");
		 	Possession corns = new Possession("corn", ico.get(ico.size()-1), "corn", 33, this, categories);
		 	addCard(corns);
		 	categories.clear();
		
		 	ico.add(ImageIO.read(new File("pics/acorn_squash.png")));
			categories.add("Squash");
		 	categories.add("green");
		 	Possession acornSquashes = new Possession("acorn squashes", ico.get(ico.size()-1), "acorn squashes", 34, this, categories);
		 	addCard(acornSquashes);
		 	categories.clear();
		
		 	ico.add(ImageIO.read(new File("pics/white_pumpkin.png")));
			categories.add("squash");
		 	categories.add("white");
		 	Possession whitePumpkins = new Possession("white pumpkins", ico.get(ico.size()-1), "white pumpkins", 35, this, categories);
		 	addCard(whitePumpkins);
		 	categories.clear();
		
		 	ico.add(ImageIO.read(new File("pics/butternut_squash.png")));
			categories.add("squash");
		 	categories.add("white");
		 	Possession butternutSquashes = new Possession("butternut squashes", ico.get(ico.size()-1), "butternut squashes", 36, this, categories);
		 	addCard(butternutSquashes);
		 	categories.clear();
		
		 	ico.add(ImageIO.read(new File("pics/spaghetti_squash.png")));
			categories.add("squash");
		 	categories.add("yellow");
		 	Possession spaghettiSquashes = new Possession("spaghetti squashes", ico.get(ico.size()-1), "spaghetti squashes", 37, this, categories);
		 	addCard(spaghettiSquashes);
		 	categories.clear();
		
		 	ico.add(ImageIO.read(new File("pics/broccoli.png")));
			categories.add("veggie");
		 	categories.add("green");
		 	Possession broccolis = new Possession("broccolis", ico.get(ico.size()-1), "brocolis", 38, this, categories);
		 	addCard(broccolis);
		 	categories.clear();
		
		 	ico.add(ImageIO.read(new File("pics/spinach.png")));
			categories.add("veggie");
		 	categories.add("green");
		 	Possession spinaches = new Possession("spinaches", ico.get(ico.size()-1), "spinaches", 39, this, categories);
		 	addCard(spinaches);
		 	categories.clear();
		
		 	ico.add(ImageIO.read(new File("pics/bean_sprouts.png")));
			categories.add("legumes");
		 	categories.add("white");
		 	Possession beanSprouts = new Possession("bean sprouts", ico.get(ico.size()-1), "bean sprouts", 40, this, categories);
		 	addCard(beanSprouts);
		 	categories.clear();
		
		 	ico.add(ImageIO.read(new File("pics/asparagus.png")));
			categories.add("veggie");
		 	categories.add("green");
		 	Possession asparaguses = new Possession("asparaguses", ico.get(ico.size()-1), "asparaguses", 41, this, categories);
		 	addCard(asparaguses);
		 	categories.clear();
		
		 	ico.add(ImageIO.read(new File("pics/edamame.png")));
			categories.add("legume");
		 	categories.add("green");
		 	Possession edamames = new Possession("edamames", ico.get(ico.size()-1), "edamames", 42, this, categories);
		 	addCard(edamames);
		 	categories.clear();
		
		 	ico.add(ImageIO.read(new File("pics/green_bean.png")));
			categories.add("legumes");
		 	categories.add("green");
		 	Possession greenBeans = new Possession("green beans", ico.get(ico.size()-1), "green beans", 43, this, categories);
		 	addCard(greenBeans);
		 	categories.clear();
		
		 	ico.add(ImageIO.read(new File("pics/artichoke.png")));
			categories.add("veggie");
		 	categories.add("green");
		 	Possession artichokes = new Possession("artichokes", ico.get(ico.size()-1), "artichokes", 44, this, categories);
		 	addCard(artichokes);
		 	categories.clear();
		
		 	ico.add(ImageIO.read(new File("pics/water_chestnut.png")));
			categories.add("fruit");
		 	categories.add("white");
		 	Possession waterChestnuts = new Possession("water chestnuts", ico.get(ico.size()-1), "water chestnuts", 45, this, categories);
		 	addCard(waterChestnuts);
		 	categories.clear();
		
		 	ico.add(ImageIO.read(new File("pics/radish.png")));
			categories.add("veggie");
		 	categories.add("red");
		 	Possession radishes = new Possession("radishes", ico.get(ico.size()-1), "radishes", 46, this, categories);
		 	addCard(radishes);
		 	categories.clear();
		
		 	ico.add(ImageIO.read(new File("pics/cabbage.png")));
			categories.add("veggie");
		 	categories.add("purple");
		 	Possession cabbages = new Possession("cabbages", ico.get(ico.size()-1), "the purple kind", 47, this, categories);
		 	addCard(cabbages);
		 	categories.clear();
		
		 	ico.add(ImageIO.read(new File("pics/celery.png")));
			categories.add("veggie");
		 	categories.add("green");
		 	Possession celerys = new Possession("celery stalks", ico.get(ico.size()-1), "celery stalks", 48, this, categories);
		 	addCard(celerys);
		 	categories.clear();
		
		 	ico.add(ImageIO.read(new File("pics/kale.png")));
			categories.add("veggie");
		 	categories.add("green");
		 	Possession kales = new Possession("kale leaves", ico.get(ico.size()-1), "kale leaves", 49, this, categories);
		 	addCard(kales);
		 	categories.clear();
		
		 	ico.add(ImageIO.read(new File("pics/pumpkin.png")));
			categories.add("squash");
		 	categories.add("orange");
		 	Possession pumpkins = new Possession("pumpkins", ico.get(ico.size()-1), "spooky", 50, this, categories);
		 	addCard(pumpkins);
		 	categories.clear();
		
		 	ico.add(ImageIO.read(new File("pics/peas.png")));
			categories.add("legume");
		 	categories.add("green");
		 	Possession peas = new Possession("peas", ico.get(ico.size()-1), "peas", 51, this, categories);
		 	addCard(peas);
		 	categories.clear();
		
		 	ico.add(ImageIO.read(new File("pics/beets.png")));
			categories.add("veggies");
		 	categories.add("red");
		 	Possession beets = new Possession("beets", ico.get(ico.size()-1), "check out these funky beets", 52, this, categories);
		 	addCard(beets);
		 	categories.clear();
		
		 	ico.add(ImageIO.read(new File("pics/turnip.png")));
			categories.add("veggies");
		 	categories.add("white");
		 	Possession turnips = new Possession("turnips", ico.get(ico.size()-1), "turnips", 53, this, categories);
		 	addCard(turnips);
		 	categories.clear();
		
		 	ico.add(ImageIO.read(new File("pics/cauliflower.png")));
			categories.add("veggies");
		 	categories.add("white");
		 	Possession cauliflowers = new Possession("cauliflowers", ico.get(ico.size()-1), "cauliflowers", 54, this, categories);
		 	addCard(cauliflowers);
		 	categories.clear();
		
		 	ico.add(ImageIO.read(new File("pics/potato.png")));
			categories.add("veggies");
		 	categories.add("yellow");
		 	Possession potatoes = new Possession("potatoes", ico.get(ico.size()-1), "potatoes", 55, this, categories);
		 	addCard(potatoes);
		 	categories.clear();
		
		 	//begin making goals
		 	ArrayList<Possession> winCards = new ArrayList<Possession>();//the list that handles the possession cards needed for each goal
		
		 	winCards.add(apples);//the possessions needed for each goal
		 	winCards.add(bananas);
		 	Goal iLikeToOot = new Goal(g, "I like to oot oot opples and baynaynays", goalImg, "apples and bananas", 56, this, (ArrayList<Possession>) winCards.clone());//makes the goal card
		 	addCard(iLikeToOot);//adds the card to the deck
		 	winCards.clear();//clears the cards needed to win from each card
		
		 	winCards.add(starfruits);
		 	winCards.add(passionfruits);
		 	Goal RnJ = new Goal(g, "Romeo and Juliet", goalImg, "starfruits and passionfruits", 57, this, (ArrayList<Possession>) winCards.clone());
		 	addCard(RnJ);
		 	winCards.clear();
		
		 	winCards.add(cucumbers);
		 	winCards.add(corns);
		 	winCards.add(butternutSquashes);
		 	Goal penis = new Goal(g, "Oddly Phallic", goalImg, "cucumbers and corn and butternut squashes", 58, this, (ArrayList<Possession>) winCards.clone());
		 	addCard(penis);
		 	winCards.clear();
		
		 	winCards.add(jalapenos);
		 	winCards.add(chilis);
		 	Goal spicy = new Goal(g, "Spicy", goalImg, "jalapenos and chilis", 59, this, (ArrayList<Possession>) winCards.clone());
		 	addCard(spicy);
		 	winCards.clear();
		
		 	winCards.add(apples);
		 	winCards.add(grapes);
		 	Goal grapples = new Goal(g, "Grapples", goalImg, "grapes and apples", 60, this, (ArrayList<Possession>) winCards.clone());
		 	addCard(grapples);
		 	winCards.clear();
		
		 	winCards.add(grapefruits);
		 	winCards.add(tangerines);
		 	Goal tangelo = new Goal(g, "tangelo", goalImg, "grapefruits and tangerines", 61, this, (ArrayList<Possession>) winCards.clone());
		 	addCard(tangelo);
		 	winCards.clear();
		
		 	winCards.add(limes);
		 	winCards.add(coconuts);
		 	Goal shake = new Goal(g, "Shake it all up", goalImg, "limes and coconuts", 62, this, (ArrayList<Possession>) winCards.clone());
		 	addCard(shake);
		 	winCards.clear();
		
		 	winCards.add(lemons);
		 	winCards.add(limes);
		 	Goal sour = new Goal(g, "Sourrrr", goalImg, "lemons and limes", 63, this, (ArrayList<Possession>) winCards.clone());
		 	addCard(sour);
		 	winCards.clear();
		
		 	winCards.add(raspberries);
		 	winCards.add(blueberries);
		 	winCards.add(strawberries);
		 	Goal berryBlast = new Goal(g, "berry blast", goalImg, "raspberries and blueberries and strawberries", 64, this, (ArrayList<Possession>) winCards.clone());
		 	addCard(berryBlast);
		 	winCards.clear();
		
		 	winCards.add(oranges);
		 	Goal rhyme = new Goal(g, "Nothing rhymes with...", goalImg, "oranges", 65, this, (ArrayList<Possession>) winCards.clone());
		 	addCard(rhyme);
		 	winCards.clear();
		
		 	winCards.add(watermelons);
		 	winCards.add(cantaloupes);
		 	Goal melons = new Goal(g, "Nice melons", goalImg, "watermelons and cantaloupes", 66, this, (ArrayList<Possession>) winCards.clone());
		 	addCard(melons);
		 	winCards.clear();
		
		 	winCards.add(pears);
		 	winCards.add(cantaloupes);
		 	Goal elope = new Goal(g, "the pear cantaloupe", goalImg, "pears and cantaloupes", 67, this, (ArrayList<Possession>) winCards.clone());
		 	addCard(sour);
		 	winCards.clear();
		
		 	winCards.add(coconuts);
		 	winCards.add(cocoaNuts);
		 	Goal cocomo = new Goal(g, "way down in cocomo", goalImg, "coconuts and cocoa nuts", 68, this, (ArrayList<Possession>) winCards.clone());
		 	addCard(cocomo);
		 	winCards.clear();
		
		 	winCards.add(pineapples);
		 	winCards.add(durians);
			Goal pointy = new Goal(g, "Pointy", goalImg, "pineapples and durians", 69, this, (ArrayList<Possession>) winCards.clone());
			addCard(pointy);
			winCards.clear();
		
			winCards.add(figs);
			winCards.add(grapes);
			Goal dried = new Goal(g, "dried", goalImg, "grapes and figs", 70, this, (ArrayList<Possession>) winCards.clone());
			addCard(dried);
			winCards.clear();
		
			winCards.add(tomatoes);
			winCards.add(potatoes);
			winCards.add(eggplants);
			Goal nightshades = new Goal(g, "nightshades", goalImg, "tomatoes and potatoes and eggplants", 71, this, (ArrayList<Possession>) winCards.clone());
			addCard(nightshades);
			winCards.clear();
		
			winCards.add(tomatoes);
			winCards.add(avocados);
			Goal guac = new Goal(g, "guacamole", goalImg, "tomatoes and avocados", 72, this, (ArrayList<Possession>) winCards.clone());
			addCard(guac);
			winCards.clear();
		
			winCards.add(carrots);
			winCards.add(turnips);
			Goal roots = new Goal(g, "get back to your roots", goalImg, "carrots and turnips", 73, this, (ArrayList<Possession>) winCards.clone());
			addCard(roots);
			winCards.clear();
		
			winCards.add(beets);
			winCards.add(radishes);
			Goal roots2 = new Goal(g, "get back to your roots part II", goalImg, "beets and radishes", 74, this, (ArrayList<Possession>) winCards.clone());
			addCard(roots2);
			winCards.clear();
		
			winCards.add(potatoes);
			winCards.add(tomatoes);
			Goal toes = new Goal(g, "toes", goalImg, "tomatoes and potatoes", 75, this, (ArrayList<Possession>) winCards.clone());
			addCard(toes);
			winCards.clear();
		
			winCards.add(pomegranates);
			winCards.add(artichokes);
			Goal heart = new Goal(g, "to the heart of the matter", goalImg, "pomegranates and artichokes", 76, this, (ArrayList<Possession>) winCards.clone());
			addCard(heart);
			winCards.clear();
		
			winCards.add(broccolis);
			winCards.add(cauliflowers);
			Goal diff = new Goal(g, "what's the difference", goalImg, "broccolis and cauliflowers", 77, this, (ArrayList<Possession>) winCards.clone());
			addCard(diff);
			winCards.clear();
		
			winCards.add(cabbages);
			winCards.add(spinaches);
			winCards.add(kales);
			Goal leaf = new Goal(g, "leafy", goalImg, "cabbage and kale and spinach", 78, this, (ArrayList<Possession>) winCards.clone());
			addCard(leaf);
			winCards.clear();
		
			winCards.add(peas);
			winCards.add(chickpeas);
			winCards.add(snowPeas);
			Goal peace = new Goal(g, "give peas a chance", goalImg, "peas and chick peas and snow peas", 79, this, (ArrayList<Possession>) winCards.clone());
			addCard(peace);
			winCards.clear();
		
			winCards.add(waterChestnuts);
			winCards.add(peanuts);
			Goal nuts = new Goal(g, "not nuts", goalImg, "peanuts and water chestnuts", 80, this, (ArrayList<Possession>) winCards.clone());
			addCard(nuts);
			winCards.clear();
		
			winCards.add(celerys);
			winCards.add(asparaguses);
			Goal stalk = new Goal(g, "stalker", goalImg, "celery and aspharagus", 81, this, (ArrayList<Possession>) winCards.clone());
			addCard(stalk);
			winCards.clear();
		
			winCards.add(pumpkins);
			winCards.add(whitePumpkins);
			Goal spook = new Goal(g, "spooky scary", goalImg, "pumpkins and white pumpkins", 82, this, (ArrayList<Possession>) winCards.clone());
			addCard(spook);
			winCards.clear();
		
			winCards.add(limaBeans);
			winCards.add(greenBeans);
			winCards.add(beanSprouts);
			Goal magic = new Goal(g, "the magical fruit", goalImg, "lima beans and green beans and bean sprouts", 83, this, (ArrayList<Possession>) winCards.clone());
			addCard(magic);
			winCards.clear();
		
			winCards.add(edamames);
			winCards.add(eggplants);
			Goal e = new Goal(g, "EEEEEEEEEEE!", goalImg, "edamames and eggplants", 84, this, (ArrayList<Possession>) winCards.clone());
			addCard(e);
			winCards.clear();
		
			winCards.add(acornSquashes);
			winCards.add(spaghettiSquashes);
			Goal lies = new Goal(g, "LIES!", goalImg, "acorn squash and spaghetti squash", 85, this, (ArrayList<Possession>) winCards.clone());
			addCard(lies);
			winCards.clear();
		
			winCards.add(grapes);
			winCards.add(grapefruits);
			Goal fail = new Goal(g, "I fail to see the resemblance", goalImg, "grapes and grapefruits", 86, this, (ArrayList<Possession>) (ArrayList<Possession>) winCards.clone());
			addCard(fail);
			winCards.clear();
		
			//rule cards
			
			//cards dictating how many cards can be played
			RuleCard play1 = new RuleCard("Play 1", ruleImg, "you may play one card now", 87, this, 1, 1);
			RuleCard play2 = new RuleCard("Play 2", ruleImg, "you may play two cards now", 88, this, 1, 2);
			RuleCard play3 = new RuleCard("Play 3", ruleImg, "you may play three cards now", 89, this, 1, 3);
			RuleCard play4 = new RuleCard("Play 4", ruleImg, "you may play four cards now", 90, this, 1, 4);
			
			//cards dictating how many cards can be drawn
			RuleCard draw1 = new RuleCard("Draw 1", ruleImg, "you may draw one card now", 91, this, 2, 1);
			RuleCard draw2 = new RuleCard("Draw 2", ruleImg, "you may draw two cards now", 92, this, 2, 2);
			RuleCard draw3 = new RuleCard("Draw 3", ruleImg, "you may draw three cards now", 93, this, 2, 3);
			RuleCard draw4 = new RuleCard("Draw 4", ruleImg, "you may draw four cards now", 94, this, 2, 4);
			
			//cards dictating how many possessions can be had in your holding pen
			//RuleCard posLim2 = new RuleCard("possession limit 2", ruleImg, "you may have up to two possessions in your pen", 95, this, 3, 2);
			RuleCard posLim3 = new RuleCard("possession limit 3", ruleImg, "you may have up to three possessions in your pen", 95, this, 3, 3);
			RuleCard posLim4 = new RuleCard("possession limit 4", ruleImg, "you may have up to four possessions in your pen", 96, this, 3, 4);
			RuleCard posLim5 = new RuleCard("possession limit 5", ruleImg, "you may have up to five possessions in your pen", 97, this, 3, 5);
			
			//cards dictating how many cards can be held in your hand
			RuleCard handLim1 = new RuleCard("hand limit 1", ruleImg, "you may have up to one card in your hand", 98, this, 4, 1);
			RuleCard handLim2 = new RuleCard("hand limit 2", ruleImg, "you may have up to two cards in your hand", 99, this, 4, 2);
			RuleCard handLim3 = new RuleCard("hand limit 3", ruleImg, "you may have up to three cards in your hand", 100, this, 4, 3);
			RuleCard handLim4 = new RuleCard("hand limit 4", ruleImg, "you may have up to four cards in your hand", 101, this, 4, 4);
		
			//add the cards to the deck
			addCard(play1);
			addCard(play2);
			addCard(play3);
			addCard(play4);
			addCard(draw1);
			addCard(draw2);
			addCard(draw3);
			addCard(draw4);
			addCard(posLim3);
			addCard(posLim4);
			addCard(posLim5);
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
	}//end putInCard()

}
