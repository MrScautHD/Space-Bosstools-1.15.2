
package net.mcreator.boss_tools.gui;

import org.lwjgl.opengl.GL11;

import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.container.Slot;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Container;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.Minecraft;

import net.mcreator.boss_tools.procedures.GeneratorEngery9Procedure;
import net.mcreator.boss_tools.procedures.GeneratorEngery8Procedure;
import net.mcreator.boss_tools.procedures.GeneratorEngery7Procedure;
import net.mcreator.boss_tools.procedures.GeneratorEngery6Procedure;
import net.mcreator.boss_tools.procedures.GeneratorEngery5Procedure;
import net.mcreator.boss_tools.procedures.GeneratorEngery4Procedure;
import net.mcreator.boss_tools.procedures.GeneratorEngery3Procedure;
import net.mcreator.boss_tools.procedures.GeneratorEngery2Procedure;
import net.mcreator.boss_tools.procedures.GeneratorEngery23Procedure;
import net.mcreator.boss_tools.procedures.GeneratorEngery22Procedure;
import net.mcreator.boss_tools.procedures.GeneratorEngery21Procedure;
import net.mcreator.boss_tools.procedures.GeneratorEngery20Procedure;
import net.mcreator.boss_tools.procedures.GeneratorEngery19Procedure;
import net.mcreator.boss_tools.procedures.GeneratorEngery18Procedure;
import net.mcreator.boss_tools.procedures.GeneratorEngery17Procedure;
import net.mcreator.boss_tools.procedures.GeneratorEngery16Procedure;
import net.mcreator.boss_tools.procedures.GeneratorEngery15Procedure;
import net.mcreator.boss_tools.procedures.GeneratorEngery14Procedure;
import net.mcreator.boss_tools.procedures.GeneratorEngery13Procedure;
import net.mcreator.boss_tools.procedures.GeneratorEngery12Procedure;
import net.mcreator.boss_tools.procedures.GeneratorEngery11Procedure;
import net.mcreator.boss_tools.procedures.GeneratorEngery10Procedure;
import net.mcreator.boss_tools.procedures.GeneratorEnergyGui1Procedure;
import net.mcreator.boss_tools.procedures.Fueltank9Procedure;
import net.mcreator.boss_tools.procedures.Fueltank8Procedure;
import net.mcreator.boss_tools.procedures.Fueltank7Procedure;
import net.mcreator.boss_tools.procedures.Fueltank6Procedure;
import net.mcreator.boss_tools.procedures.Fueltank5Procedure;
import net.mcreator.boss_tools.procedures.Fueltank4Procedure;
import net.mcreator.boss_tools.procedures.Fueltank45Procedure;
import net.mcreator.boss_tools.procedures.Fueltank44Procedure;
import net.mcreator.boss_tools.procedures.Fueltank43Procedure;
import net.mcreator.boss_tools.procedures.Fueltank42Procedure;
import net.mcreator.boss_tools.procedures.Fueltank41Procedure;
import net.mcreator.boss_tools.procedures.Fueltank40Procedure;
import net.mcreator.boss_tools.procedures.Fueltank3Procedure;
import net.mcreator.boss_tools.procedures.Fueltank39Procedure;
import net.mcreator.boss_tools.procedures.Fueltank38Procedure;
import net.mcreator.boss_tools.procedures.Fueltank37Procedure;
import net.mcreator.boss_tools.procedures.Fueltank36Procedure;
import net.mcreator.boss_tools.procedures.Fueltank35Procedure;
import net.mcreator.boss_tools.procedures.Fueltank34Procedure;
import net.mcreator.boss_tools.procedures.Fueltank33Procedure;
import net.mcreator.boss_tools.procedures.Fueltank32Procedure;
import net.mcreator.boss_tools.procedures.Fueltank31Procedure;
import net.mcreator.boss_tools.procedures.Fueltank30Procedure;
import net.mcreator.boss_tools.procedures.Fueltank2Procedure;
import net.mcreator.boss_tools.procedures.Fueltank29Procedure;
import net.mcreator.boss_tools.procedures.Fueltank28Procedure;
import net.mcreator.boss_tools.procedures.Fueltank27Procedure;
import net.mcreator.boss_tools.procedures.Fueltank26Procedure;
import net.mcreator.boss_tools.procedures.Fueltank25Procedure;
import net.mcreator.boss_tools.procedures.Fueltank24Procedure;
import net.mcreator.boss_tools.procedures.Fueltank23Procedure;
import net.mcreator.boss_tools.procedures.Fueltank22Procedure;
import net.mcreator.boss_tools.procedures.Fueltank21Procedure;
import net.mcreator.boss_tools.procedures.Fueltank20Procedure;
import net.mcreator.boss_tools.procedures.Fueltank19Procedure;
import net.mcreator.boss_tools.procedures.Fueltank18Procedure;
import net.mcreator.boss_tools.procedures.Fueltank17Procedure;
import net.mcreator.boss_tools.procedures.Fueltank16Procedure;
import net.mcreator.boss_tools.procedures.Fueltank15Procedure;
import net.mcreator.boss_tools.procedures.Fueltank14Procedure;
import net.mcreator.boss_tools.procedures.Fueltank13Procedure;
import net.mcreator.boss_tools.procedures.Fueltank12Procedure;
import net.mcreator.boss_tools.procedures.Fueltank11Procedure;
import net.mcreator.boss_tools.procedures.Fueltank10Procedure;
import net.mcreator.boss_tools.BossToolsModElements;
import net.mcreator.boss_tools.BossToolsMod;

import java.util.function.Supplier;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map;
import java.util.HashMap;

import com.google.common.collect.ImmutableMap;

@BossToolsModElements.ModElement.Tag
public class FuelRefineryGUIGui extends BossToolsModElements.ModElement {
	public static HashMap guistate = new HashMap();
	private static ContainerType<GuiContainerMod> containerType = null;
	public FuelRefineryGUIGui(BossToolsModElements instance) {
		super(instance, 442);
		elements.addNetworkMessage(ButtonPressedMessage.class, ButtonPressedMessage::buffer, ButtonPressedMessage::new,
				ButtonPressedMessage::handler);
		elements.addNetworkMessage(GUISlotChangedMessage.class, GUISlotChangedMessage::buffer, GUISlotChangedMessage::new,
				GUISlotChangedMessage::handler);
		containerType = new ContainerType<>(new GuiContainerModFactory());
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@OnlyIn(Dist.CLIENT)
	public void initElements() {
		DeferredWorkQueue.runLater(() -> ScreenManager.registerFactory(containerType, GuiWindow::new));
	}

	@SubscribeEvent
	public void registerContainer(RegistryEvent.Register<ContainerType<?>> event) {
		event.getRegistry().register(containerType.setRegistryName("fuel_refinery_gui"));
	}
	public static class GuiContainerModFactory implements IContainerFactory {
		public GuiContainerMod create(int id, PlayerInventory inv, PacketBuffer extraData) {
			return new GuiContainerMod(id, inv, extraData);
		}
	}

	public static class GuiContainerMod extends Container implements Supplier<Map<Integer, Slot>> {
		private World world;
		private PlayerEntity entity;
		private int x, y, z;
		private IItemHandler internal;
		private Map<Integer, Slot> customSlots = new HashMap<>();
		private boolean bound = false;
		public GuiContainerMod(int id, PlayerInventory inv, PacketBuffer extraData) {
			super(containerType, id);
			this.entity = inv.player;
			this.world = inv.player.world;
			this.internal = new ItemStackHandler(2);
			BlockPos pos = null;
			if (extraData != null) {
				pos = extraData.readBlockPos();
				this.x = pos.getX();
				this.y = pos.getY();
				this.z = pos.getZ();
			}
			if (pos != null) {
				if (extraData.readableBytes() == 1) { // bound to item
					byte hand = extraData.readByte();
					ItemStack itemstack;
					if (hand == 0)
						itemstack = this.entity.getHeldItemMainhand();
					else
						itemstack = this.entity.getHeldItemOffhand();
					itemstack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
						this.internal = capability;
						this.bound = true;
					});
				} else if (extraData.readableBytes() > 1) {
					extraData.readByte(); // drop padding
					Entity entity = world.getEntityByID(extraData.readVarInt());
					if (entity != null)
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
							this.internal = capability;
							this.bound = true;
						});
				} else { // might be bound to block
					TileEntity ent = inv.player != null ? inv.player.world.getTileEntity(pos) : null;
					if (ent != null) {
						ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
							this.internal = capability;
							this.bound = true;
						});
					}
				}
			}
			this.customSlots.put(0, this.addSlot(new SlotItemHandler(internal, 0, 76, 40) {
			}));
			this.customSlots.put(1, this.addSlot(new SlotItemHandler(internal, 1, 8, 60) {
			}));
			int si;
			int sj;
			for (si = 0; si < 3; ++si)
				for (sj = 0; sj < 9; ++sj)
					this.addSlot(new Slot(inv, sj + (si + 1) * 9, 0 + 8 + sj * 18, 6 + 84 + si * 18));
			for (si = 0; si < 9; ++si)
				this.addSlot(new Slot(inv, si, 0 + 8 + si * 18, 6 + 142));
		}

		public Map<Integer, Slot> get() {
			return customSlots;
		}

		@Override
		public boolean canInteractWith(PlayerEntity player) {
			return true;
		}

		@Override
		public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
			ItemStack itemstack = ItemStack.EMPTY;
			Slot slot = (Slot) this.inventorySlots.get(index);
			if (slot != null && slot.getHasStack()) {
				ItemStack itemstack1 = slot.getStack();
				itemstack = itemstack1.copy();
				if (index < 2) {
					if (!this.mergeItemStack(itemstack1, 2, this.inventorySlots.size(), true)) {
						return ItemStack.EMPTY;
					}
					slot.onSlotChange(itemstack1, itemstack);
				} else if (!this.mergeItemStack(itemstack1, 0, 2, false)) {
					if (index < 2 + 27) {
						if (!this.mergeItemStack(itemstack1, 2 + 27, this.inventorySlots.size(), true)) {
							return ItemStack.EMPTY;
						}
					} else {
						if (!this.mergeItemStack(itemstack1, 2, 2 + 27, false)) {
							return ItemStack.EMPTY;
						}
					}
					return ItemStack.EMPTY;
				}
				if (itemstack1.getCount() == 0) {
					slot.putStack(ItemStack.EMPTY);
				} else {
					slot.onSlotChanged();
				}
				if (itemstack1.getCount() == itemstack.getCount()) {
					return ItemStack.EMPTY;
				}
				slot.onTake(playerIn, itemstack1);
			}
			return itemstack;
		}

		@Override /**
					 * Merges provided ItemStack with the first avaliable one in the
					 * container/player inventor between minIndex (included) and maxIndex
					 * (excluded). Args : stack, minIndex, maxIndex, negativDirection. /!\ the
					 * Container implementation do not check if the item is valid for the slot
					 */
		protected boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection) {
			boolean flag = false;
			int i = startIndex;
			if (reverseDirection) {
				i = endIndex - 1;
			}
			if (stack.isStackable()) {
				while (!stack.isEmpty()) {
					if (reverseDirection) {
						if (i < startIndex) {
							break;
						}
					} else if (i >= endIndex) {
						break;
					}
					Slot slot = this.inventorySlots.get(i);
					ItemStack itemstack = slot.getStack();
					if (slot.isItemValid(itemstack) && !itemstack.isEmpty() && areItemsAndTagsEqual(stack, itemstack)) {
						int j = itemstack.getCount() + stack.getCount();
						int maxSize = Math.min(slot.getSlotStackLimit(), stack.getMaxStackSize());
						if (j <= maxSize) {
							stack.setCount(0);
							itemstack.setCount(j);
							slot.putStack(itemstack);
							flag = true;
						} else if (itemstack.getCount() < maxSize) {
							stack.shrink(maxSize - itemstack.getCount());
							itemstack.setCount(maxSize);
							slot.putStack(itemstack);
							flag = true;
						}
					}
					if (reverseDirection) {
						--i;
					} else {
						++i;
					}
				}
			}
			if (!stack.isEmpty()) {
				if (reverseDirection) {
					i = endIndex - 1;
				} else {
					i = startIndex;
				}
				while (true) {
					if (reverseDirection) {
						if (i < startIndex) {
							break;
						}
					} else if (i >= endIndex) {
						break;
					}
					Slot slot1 = this.inventorySlots.get(i);
					ItemStack itemstack1 = slot1.getStack();
					if (itemstack1.isEmpty() && slot1.isItemValid(stack)) {
						if (stack.getCount() > slot1.getSlotStackLimit()) {
							slot1.putStack(stack.split(slot1.getSlotStackLimit()));
						} else {
							slot1.putStack(stack.split(stack.getCount()));
						}
						slot1.onSlotChanged();
						flag = true;
						break;
					}
					if (reverseDirection) {
						--i;
					} else {
						++i;
					}
				}
			}
			return flag;
		}

		@Override
		public void onContainerClosed(PlayerEntity playerIn) {
			super.onContainerClosed(playerIn);
			if (!bound && (playerIn instanceof ServerPlayerEntity)) {
				if (!playerIn.isAlive() || playerIn instanceof ServerPlayerEntity && ((ServerPlayerEntity) playerIn).hasDisconnected()) {
					for (int j = 0; j < internal.getSlots(); ++j) {
						playerIn.dropItem(internal.extractItem(j, internal.getStackInSlot(j).getCount(), false), false);
					}
				} else {
					for (int i = 0; i < internal.getSlots(); ++i) {
						playerIn.inventory.placeItemBackInInventory(playerIn.world,
								internal.extractItem(i, internal.getStackInSlot(i).getCount(), false));
					}
				}
			}
		}

		private void slotChanged(int slotid, int ctype, int meta) {
			if (this.world != null && this.world.isRemote) {
				BossToolsMod.PACKET_HANDLER.sendToServer(new GUISlotChangedMessage(slotid, x, y, z, ctype, meta));
				handleSlotAction(entity, slotid, ctype, meta, x, y, z);
			}
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class GuiWindow extends ContainerScreen<GuiContainerMod> {
		private World world;
		private int x, y, z;
		private PlayerEntity entity;
		public GuiWindow(GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
			super(container, inventory, text);
			this.world = container.world;
			this.x = container.x;
			this.y = container.y;
			this.z = container.z;
			this.entity = container.entity;
			this.xSize = 177;
			this.ySize = 172;
		}
		private static final ResourceLocation texture = new ResourceLocation("boss_tools:textures/fuel_refinery_gui.png");
		@Override
		public void render(int mouseX, int mouseY, float partialTicks) {
			this.renderBackground();
			super.render(mouseX, mouseY, partialTicks);
			this.renderHoveredToolTip(mouseX, mouseY);
		}

		@Override
		protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
			GL11.glColor4f(1, 1, 1, 1);
			Minecraft.getInstance().getTextureManager().bindTexture(texture);
			int k = (this.width - this.xSize) / 2;
			int l = (this.height - this.ySize) / 2;
			this.blit(k, l, 0, 0, this.xSize, this.ySize, this.xSize, this.ySize);
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/energyoutputslot.png"));
			this.blit(this.guiLeft + 138, this.guiTop + 58, 0, 0, 32, 21, 32, 21);
			Minecraft.getInstance().getTextureManager()
					.bindTexture(new ResourceLocation("boss_tools:textures/energy_volume_fractional_vertical_bar_background.png"));
			this.blit(this.guiLeft + 141, this.guiTop + 11, 0, 0, 24, 48, 24, 48);
			if (GeneratorEnergyGui1Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/energyfull0.png"));
				this.blit(this.guiLeft + 141, this.guiTop + 11, 0, 0, 24, 48, 24, 48);
			}
			if (GeneratorEngery2Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/energyfull1.png"));
				this.blit(this.guiLeft + 141, this.guiTop + 11, 0, 0, 24, 48, 24, 48);
			}
			if (GeneratorEngery3Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/energyfull2.png"));
				this.blit(this.guiLeft + 141, this.guiTop + 11, 0, 0, 24, 48, 24, 48);
			}
			if (GeneratorEngery4Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/energyfull3.png"));
				this.blit(this.guiLeft + 141, this.guiTop + 11, 0, 0, 24, 48, 24, 48);
			}
			if (GeneratorEngery5Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/energyfull4.png"));
				this.blit(this.guiLeft + 141, this.guiTop + 11, 0, 0, 24, 48, 24, 48);
			}
			if (GeneratorEngery6Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/energyfull5.png"));
				this.blit(this.guiLeft + 141, this.guiTop + 11, 0, 0, 24, 48, 24, 48);
			}
			if (GeneratorEngery7Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/energyfull6.png"));
				this.blit(this.guiLeft + 141, this.guiTop + 11, 0, 0, 24, 48, 24, 48);
			}
			if (GeneratorEngery8Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/energyfull7.png"));
				this.blit(this.guiLeft + 141, this.guiTop + 11, 0, 0, 24, 48, 24, 48);
			}
			if (GeneratorEngery9Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/energyfull8.png"));
				this.blit(this.guiLeft + 141, this.guiTop + 11, 0, 0, 24, 48, 24, 48);
			}
			if (GeneratorEngery10Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/energyfull9.png"));
				this.blit(this.guiLeft + 141, this.guiTop + 11, 0, 0, 24, 48, 24, 48);
			}
			if (GeneratorEngery11Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/energyfull10.png"));
				this.blit(this.guiLeft + 141, this.guiTop + 11, 0, 0, 24, 48, 24, 48);
			}
			if (GeneratorEngery12Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/energyfull11.png"));
				this.blit(this.guiLeft + 141, this.guiTop + 11, 0, 0, 24, 48, 24, 48);
			}
			if (GeneratorEngery13Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/energyfull12.png"));
				this.blit(this.guiLeft + 141, this.guiTop + 11, 0, 0, 24, 48, 24, 48);
			}
			if (GeneratorEngery14Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/energyfull13.png"));
				this.blit(this.guiLeft + 141, this.guiTop + 11, 0, 0, 24, 48, 24, 48);
			}
			if (GeneratorEngery15Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/energyfull14.png"));
				this.blit(this.guiLeft + 141, this.guiTop + 11, 0, 0, 24, 48, 24, 48);
			}
			if (GeneratorEngery16Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/energyfull15.png"));
				this.blit(this.guiLeft + 141, this.guiTop + 11, 0, 0, 24, 48, 24, 48);
			}
			if (GeneratorEngery17Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/energyfull16.png"));
				this.blit(this.guiLeft + 141, this.guiTop + 11, 0, 0, 24, 48, 24, 48);
			}
			if (GeneratorEngery18Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/energyfull17.png"));
				this.blit(this.guiLeft + 141, this.guiTop + 11, 0, 0, 24, 48, 24, 48);
			}
			if (GeneratorEngery19Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/energyfull18.png"));
				this.blit(this.guiLeft + 141, this.guiTop + 11, 0, 0, 24, 48, 24, 48);
			}
			if (GeneratorEngery20Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/energyfull19.png"));
				this.blit(this.guiLeft + 141, this.guiTop + 11, 0, 0, 24, 48, 24, 48);
			}
			if (GeneratorEngery21Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/energyfull20.png"));
				this.blit(this.guiLeft + 141, this.guiTop + 11, 0, 0, 24, 48, 24, 48);
			}
			if (GeneratorEngery22Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/energyfull21.png"));
				this.blit(this.guiLeft + 141, this.guiTop + 11, 0, 0, 24, 48, 24, 48);
			}
			if (GeneratorEngery23Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/energyfull22.png"));
				this.blit(this.guiLeft + 141, this.guiTop + 11, 0, 0, 24, 48, 24, 48);
			}
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new.png"));
			this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			if (Fueltank2Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_43.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank3Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_42.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank4Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_41.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank5Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_40.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank6Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_39.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank7Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_38.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank8Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_37.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank9Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_36.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank10Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_35.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank11Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_34.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank12Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_33.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank13Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_32.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank14Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_31.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank15Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_30.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank16Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_29.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank17Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_28.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank18Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_27.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank19Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_26.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank20Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_25.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank21Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_24.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank22Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_23.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank23Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_22.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank24Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_21.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank25Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_20.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank26Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_19.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank27Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_18.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank28Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_17.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank29Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_16.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank30Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_15.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank31Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_14.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank32Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_13.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank33Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_12.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank34Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_11.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank35Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_10.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank36Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_9.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank37Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_8.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank38Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_7.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank39Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_6.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank40Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_5.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank41Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_4.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank42Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_3.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank43Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_2.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank44Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_new2_1.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
			if (Fueltank45Procedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("boss_tools:textures/fuel_refinery_fuel_full_new2.png"));
				this.blit(this.guiLeft + 9, this.guiTop + 11, 0, 0, 14, 48, 14, 48);
			}
		}

		@Override
		public boolean keyPressed(int key, int b, int c) {
			if (key == 256) {
				this.minecraft.player.closeScreen();
				return true;
			}
			return super.keyPressed(key, b, c);
		}

		@Override
		public void tick() {
			super.tick();
		}

		@Override
		protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
			this.font.drawString("Fuel Refinery", 50, 7, -13421773);
			this.font.drawString("Inventory", 7, 79, -13421773);
			this.font.drawString("" + (new Object() {
				public int getEnergyStored(BlockPos pos) {
					AtomicInteger _retval = new AtomicInteger(0);
					TileEntity _ent = world.getTileEntity(pos);
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> _retval.set(capability.getEnergyStored()));
					return _retval.get();
				}
			}.getEnergyStored(new BlockPos((int) x, (int) y, (int) z))) + "", 141, 65, -12829636);
		}

		@Override
		public void removed() {
			super.removed();
			Minecraft.getInstance().keyboardListener.enableRepeatEvents(false);
		}

		@Override
		public void init(Minecraft minecraft, int width, int height) {
			super.init(minecraft, width, height);
			minecraft.keyboardListener.enableRepeatEvents(true);
		}
	}

	public static class ButtonPressedMessage {
		int buttonID, x, y, z;
		public ButtonPressedMessage(PacketBuffer buffer) {
			this.buttonID = buffer.readInt();
			this.x = buffer.readInt();
			this.y = buffer.readInt();
			this.z = buffer.readInt();
		}

		public ButtonPressedMessage(int buttonID, int x, int y, int z) {
			this.buttonID = buttonID;
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public static void buffer(ButtonPressedMessage message, PacketBuffer buffer) {
			buffer.writeInt(message.buttonID);
			buffer.writeInt(message.x);
			buffer.writeInt(message.y);
			buffer.writeInt(message.z);
		}

		public static void handler(ButtonPressedMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				PlayerEntity entity = context.getSender();
				int buttonID = message.buttonID;
				int x = message.x;
				int y = message.y;
				int z = message.z;
				handleButtonAction(entity, buttonID, x, y, z);
			});
			context.setPacketHandled(true);
		}
	}

	public static class GUISlotChangedMessage {
		int slotID, x, y, z, changeType, meta;
		public GUISlotChangedMessage(int slotID, int x, int y, int z, int changeType, int meta) {
			this.slotID = slotID;
			this.x = x;
			this.y = y;
			this.z = z;
			this.changeType = changeType;
			this.meta = meta;
		}

		public GUISlotChangedMessage(PacketBuffer buffer) {
			this.slotID = buffer.readInt();
			this.x = buffer.readInt();
			this.y = buffer.readInt();
			this.z = buffer.readInt();
			this.changeType = buffer.readInt();
			this.meta = buffer.readInt();
		}

		public static void buffer(GUISlotChangedMessage message, PacketBuffer buffer) {
			buffer.writeInt(message.slotID);
			buffer.writeInt(message.x);
			buffer.writeInt(message.y);
			buffer.writeInt(message.z);
			buffer.writeInt(message.changeType);
			buffer.writeInt(message.meta);
		}

		public static void handler(GUISlotChangedMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				PlayerEntity entity = context.getSender();
				int slotID = message.slotID;
				int changeType = message.changeType;
				int meta = message.meta;
				int x = message.x;
				int y = message.y;
				int z = message.z;
				handleSlotAction(entity, slotID, changeType, meta, x, y, z);
			});
			context.setPacketHandled(true);
		}
	}
	private static void handleButtonAction(PlayerEntity entity, int buttonID, int x, int y, int z) {
		World world = entity.world;
		// security measure to prevent arbitrary chunk generation
		if (!world.isBlockLoaded(new BlockPos(x, y, z)))
			return;
	}

	private static void handleSlotAction(PlayerEntity entity, int slotID, int changeType, int meta, int x, int y, int z) {
		World world = entity.world;
		// security measure to prevent arbitrary chunk generation
		if (!world.isBlockLoaded(new BlockPos(x, y, z)))
			return;
	}
}
