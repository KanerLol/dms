package net.dms.mod.items.food;



import static net.dms.mod.items.food.ItemsFoodDec.*;
import net.dms.mod.items.SimpleItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemsFood {
	public static void init() {
		registerItems();
	}
	
	public static void registerItems(){
		foodMeat = registerItem(new FoodDMS(1, 25, -10, 1, 0, 0, 0).setUnlocalizedName("Meat").setMaxStackSize(20));
		foodCookedMeat = registerItem(new FoodDMS(3, 25, 1, 0, 0, 0, 0).setUnlocalizedName("CookedMeat").setMaxStackSize(20));
		foodJerky =registerItem(new FoodDMS(20, 25, 15, 1, 0, 0, 0).setUnlocalizedName("Jerky").setMaxStackSize(20));
		foodMonsterMeat =registerItem(new FoodDMS(-20, 19, -15, 1, 0, 0, 0).setUnlocalizedName("MonsterMeat").setMaxStackSize(20));
		foodCookedMonsterMeat =registerItem(new FoodDMS(-3, 19, -10, 1, 0, 0, 0).setUnlocalizedName("CookedMonsterMeat").setMaxStackSize(20));
		foodMonsterJerky =registerItem(new FoodDMS(-3, 19, -5, 1, 0, 0, 0).setUnlocalizedName("Jerky").setMaxStackSize(20));
		foodLeafyMeat =registerItem(new FoodDMS(0, 12, -10, 0, 0, 0, 0).setUnlocalizedName("LeafyMeat").setMaxStackSize(20));
		foodCookedLeafyMeat =registerItem(new FoodDMS(1, 19, 0, 0, 0, 0, 0).setUnlocalizedName("CookedLeafyMeat").setMaxStackSize(20));
		foodSmallJerky = registerItem(new FoodDMS(8, 13, 10, 0.5f, 0, 0, 0).setUnlocalizedName("SmallJerky").setMaxStackSize(40));
		foodSmallJerky = registerItem(new FoodDMS(8, 13, 10, 0.5f, 0, 0, 0).setUnlocalizedName("SmallJerky").setMaxStackSize(40));
	
	}
	
	public static Item registerItem(Item item) {
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
		
		return item;
	}
}

/*
public FoodDMS foodMeat;
public FoodDMS foodCookedMeat;
public FoodDMS foodJerky;
public FoodDMS foodMonsterMeat;
public FoodDMS foodCookedMonsterMeat;
public FoodDMS foodMonsterJerky;
public FoodDMS foodLeafyMeat;
public FoodDMS foodCookedLeafyMeat;
public FoodDMS foodSmallJerky;
public FoodDMS foodMorsel;
public FoodDMS foodCookedMorsel;
public FoodDMS foodDrumstick;
public FoodDMS foodFriedDrumstick;
public FoodDMS foodFish;
public FoodDMS foodCookedFish;
public FoodDMS foodEel;
public FoodDMS foodCookedEel;
public FoodDMS foodFrogLegs;
public FoodDMS foodCookedFrogLegs;
public FoodDMS foodBatiliskWing;
public FoodDMS foodCookedBatiliskWing;
public FoodDMS foodKoalefantTrunk;
public FoodDMS foodCookedKoalefantTrunk;
public FoodDMS foodWinterKoalefantTrunk;
public FoodDMS foodGuariansHorn;
public FoodDMS foodDeerclopsEyeBall;
public FoodDMS foodEgg;
public FoodDMS foodCookedEgg;
public FoodDMS foodTallbirdEgg;
public FoodDMS foodFriedTallbirdEgg;
*/