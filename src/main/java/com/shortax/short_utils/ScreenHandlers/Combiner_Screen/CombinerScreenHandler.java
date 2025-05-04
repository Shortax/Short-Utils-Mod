package com.shortax.short_utils.ScreenHandlers.Combiner_Screen;

import com.shortax.short_utils.Initializers.ModScreenHandlers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;

public class CombinerScreenHandler extends ScreenHandler {

    private final Inventory inventory = this.createInputInventory(3);
    private boolean combine_success;
    private static final int[] inputIndex = {0,1};

    public CombinerScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.create(playerInventory.player.getWorld(),playerInventory.player.getBlockPos()));
    }

    @SuppressWarnings("unused")
    public CombinerScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(ModScreenHandlers.COMBINER_SCREEN_HANDLER, syncId);

        combine_success = false;

        add_Input_slots();
        add_Output_slot();
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }


    private void add_Input_slots()
    {
        addSlot(new Combiner_Slot(this.inventory, inputIndex[0], 34,34,false));
        addSlot(new Combiner_Slot(this.inventory, inputIndex[1], 126,34,false));
    }

    private void add_Output_slot()
    {
        addSlot(new Combiner_Slot(this.inventory,2,80,34,true));
    }



    @Override
    public void onContentChanged(Inventory inventory) {
        super.onContentChanged(inventory);
        //String s = String.format(" - Left Item: %s | Right Item: %s",inventory.getStack(0).toString(),inventory.getStack(1).toString());
        //System.out.println(inventory.size() + s);
        ItemStack input1 = inventory.getStack(0);
        ItemStack input2 = inventory.getStack(1);
        ItemStack output = inventory.getStack(2);

        if(emptyItemStack(input1) || emptyItemStack(input2))
        {
            if(!emptyItemStack(output))
                inventory.setStack(2,ItemStack.EMPTY);
            this.combine_success = false;
        } else if (validRecipeTEST(input1,input2)) {
            if(emptyItemStack(output) && this.combine_success)
            {
                crafted(inventory,input1,input2);
                this.combine_success = false;
            }
            else if(emptyItemStack(output)){
                inventory.setStack(2,new ItemStack(Items.SPRUCE_LOG,2));
                this.combine_success = true;
            }
        }
    }

    private boolean emptyItemStack(ItemStack input)
    {
        return input.getItem().equals(Items.AIR) || input.getCount() == 0;
    }

    private boolean validRecipeTEST(ItemStack input1, ItemStack input2)
    {
        return input1.getItem().equals(Items.SPRUCE_LOG) || input2.getItem().equals(Items.SPRUCE_LOG);
    }

    private void crafted(Inventory inventory, ItemStack input1, ItemStack input2)
    {
        int c1 = input1.getCount();
        int c2 = input1.getCount();
        inventory.setStack(0,new ItemStack(input1.getItem(),c1-1));
        inventory.setStack(1,new ItemStack(input2.getItem(),c2-1));
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slotidx) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot moved_slot = this.slots.get(slotidx);

        if(moved_slot.hasStack())
        {
            int combInvIDXCutOff = 3;
            int outputIDX = 2;
            int maxSlotIDX = this.slots.size();

            int start;
            int end;
            //move from Combiner to inventory
            if(slotidx < combInvIDXCutOff)
            {
                start = combInvIDXCutOff;
                end = maxSlotIDX;
            }
            //move from inventory to Combiner
            else
            {
                start = 0;
                end = outputIDX;
            }

            if(this.insertItem(moved_slot.getStack(),start,end,false))
            {
                itemStack = moved_slot.getStack();
                if(slotidx == outputIDX)
                {
                    crafted(inventory,inventory.getStack(0),inventory.getStack(1));
                }
            }
        }
        return itemStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    public boolean combineSuccess() { return combine_success; }

    @SuppressWarnings("SameParameterValue")
    private SimpleInventory createInputInventory(int size) {
        return new SimpleInventory(size) {
            @Override
            public void markDirty() {
                super.markDirty();
                onContentChanged(this);
            }
        };
    }
}

class Combiner_Slot extends Slot
{
    private boolean lock;
    public Combiner_Slot(Inventory inventory, int index, int x, int y, boolean output) {
        super(inventory, index, x, y);
        this.lock = output;
    }

    public void switch_lockSlot()
    {
        lock = !lock;
    }

    @Override
    public boolean canInsert(ItemStack stack){
        return !lock;
    }
}
