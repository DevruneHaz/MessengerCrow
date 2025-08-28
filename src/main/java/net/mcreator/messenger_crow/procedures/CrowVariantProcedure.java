package net.mcreator.messenger_crow.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;

import net.mcreator.messenger_crow.init.MessengerCrowModItems;
import net.mcreator.messenger_crow.entity.CrowEntity;

import javax.annotation.Nullable;

import java.util.concurrent.atomic.AtomicReference;

@Mod.EventBusSubscriber
public class CrowVariantProcedure {
	@SubscribeEvent
	public static void onEntityTick(LivingEvent.LivingTickEvent event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof CrowEntity) {
			if ((new Object() {
				public ItemStack getItemStack(int sltid, Entity entity) {
					AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
					entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						_retval.set(capability.getStackInSlot(sltid).copy());
					});
					return _retval.get();
				}
			}.getItemStack(0, entity)).getItem() == MessengerCrowModItems.SCROLL.get() && (new Object() {
				public ItemStack getItemStack(int sltid, Entity entity) {
					AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
					entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						_retval.set(capability.getStackInSlot(sltid).copy());
					});
					return _retval.get();
				}
			}.getItemStack(0, entity)).getOrCreateTag().getBoolean("sealed") == true) {
				if ((entity.getPersistentData().getString("crowvariant")).equals("crow")) {
					entity.getPersistentData().putString("crowvariant", "messengercrow");
				} else if ((entity.getPersistentData().getString("crowvariant")).equals("phantom")) {
					entity.getPersistentData().putString("crowvariant", "messengerphantom");
				} else if ((entity.getPersistentData().getString("crowvariant")).equals("christmascrow")) {
					entity.getPersistentData().putString("crowvariant", "christmasmessengercrow");
				} else if ((entity.getPersistentData().getString("crowvariant")).equals("christmasphantom")) {
					entity.getPersistentData().putString("crowvariant", "christmasmessengerphantom");
				} else if ((entity.getPersistentData().getString("crowvariant")).equals("halloweencrow")) {
					entity.getPersistentData().putString("crowvariant", "halloweenmessengercrow");
				} else if ((entity.getPersistentData().getString("crowvariant")).equals("halloweenphantom")) {
					entity.getPersistentData().putString("crowvariant", "halloweenmessengerphantom");
				}
			} else {
				if ((entity.getPersistentData().getString("crowvariant")).equals("messengercrow")) {
					entity.getPersistentData().putString("crowvariant", "crow");
				} else if ((entity.getPersistentData().getString("crowvariant")).equals("messengerphantom")) {
					entity.getPersistentData().putString("crowvariant", "phantom");
				} else if ((entity.getPersistentData().getString("crowvariant")).equals("christmasmessengercrow")) {
					entity.getPersistentData().putString("crowvariant", "christmascrow");
				} else if ((entity.getPersistentData().getString("crowvariant")).equals("christmasmessengerphantom")) {
					entity.getPersistentData().putString("crowvariant", "christmasphantom");
				} else if ((entity.getPersistentData().getString("crowvariant")).equals("halloweenmessengercrow")) {
					entity.getPersistentData().putString("crowvariant", "halloweencrow");
				} else if ((entity.getPersistentData().getString("crowvariant")).equals("halloweenmessengerphantom")) {
					entity.getPersistentData().putString("crowvariant", "halloweenphantom");
				}
			}
			if ((entity.getPersistentData().getString("crowvariant")).equals("crow")) {
				if (entity instanceof CrowEntity animatable)
					animatable.setTexture("crow");
			} else if ((entity.getPersistentData().getString("crowvariant")).equals("phantom")) {
				if (entity instanceof CrowEntity animatable)
					animatable.setTexture("phantom_crow");
			} else if ((entity.getPersistentData().getString("crowvariant")).equals("messengercrow")) {
				if (entity instanceof CrowEntity animatable)
					animatable.setTexture("messenger_crow");
			} else if ((entity.getPersistentData().getString("crowvariant")).equals("messengerphantom")) {
				if (entity instanceof CrowEntity animatable)
					animatable.setTexture("messenger_phantom_crow");
			} else if ((entity.getPersistentData().getString("crowvariant")).equals("christmascrow")) {
				if (entity instanceof CrowEntity animatable)
					animatable.setTexture("christmas_crow");
			} else if ((entity.getPersistentData().getString("crowvariant")).equals("christmasphantom")) {
				if (entity instanceof CrowEntity animatable)
					animatable.setTexture("christmas_phantom_crow");
			} else if ((entity.getPersistentData().getString("crowvariant")).equals("christmasmessengercrow")) {
				if (entity instanceof CrowEntity animatable)
					animatable.setTexture("christmas_messenger_crow");
			} else if ((entity.getPersistentData().getString("crowvariant")).equals("christmasmessengerphantom")) {
				if (entity instanceof CrowEntity animatable)
					animatable.setTexture("christmas_messenger_phantom_crow");
			} else if ((entity.getPersistentData().getString("crowvariant")).equals("halloweencrow")) {
				if (entity instanceof CrowEntity animatable)
					animatable.setTexture("halloween_crow");
			} else if ((entity.getPersistentData().getString("crowvariant")).equals("halloweenphantom")) {
				if (entity instanceof CrowEntity animatable)
					animatable.setTexture("halloween_phantom_crow");
			} else if ((entity.getPersistentData().getString("crowvariant")).equals("halloweenmessengercrow")) {
				if (entity instanceof CrowEntity animatable)
					animatable.setTexture("halloween_messenger_crow");
			} else if ((entity.getPersistentData().getString("crowvariant")).equals("halloweenmessengerphantom")) {
				if (entity instanceof CrowEntity animatable)
					animatable.setTexture("halloween_messenger_phantom_crow");
			}
		}
	}
}
