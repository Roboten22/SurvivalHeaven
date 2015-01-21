/**
 * This file is part of survivalheaven.org, licensed under the MIT License (MIT).
 *
 * Copyright (c) SurvivalHeaven.org <http://www.survivalheaven.org>
 * Copyright (c) NordByen.info <http://www.nordbyen.info>
 * Copyright (c) l0lkj.info <http://www.l0lkj.info>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package info.nordbyen.survivalheaven.api.util;

import java.util.HashMap;

import net.minecraft.server.v1_8_R1.CraftingManager;
import net.minecraft.server.v1_8_R1.IRecipe;
import net.minecraft.server.v1_8_R1.InventoryCrafting;
import net.minecraft.server.v1_8_R1.ItemStack;
import net.minecraft.server.v1_8_R1.NBTTagCompound;
import net.minecraft.server.v1_8_R1.ShapedRecipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;

/**
 * The Class CustomShapedRecipe.
 */
public class CustomShapedRecipe extends ShapedRecipes implements IRecipe {

    /**
     * Adds the recipe.
     * 
     * @param name the name
     * @param item1 the item1
     * @param args the args
     * @return the custom shaped recipe
     */
    @SuppressWarnings("unchecked")
    public static CustomShapedRecipe addRecipe(final String name, final org.bukkit.inventory.ItemStack item1, final Object... args) {
        ItemStack item = null;
        item = CraftItemStack.asNMSCopy(item1);
        String var3 = "";
        int var4 = 0;
        int var5 = 0;
        int var6 = 0;
        int var9;
        if (args[var4] instanceof String[]) {
            final String[] var7 = (String[]) args[var4++];
            final String[] var8 = var7;
            var9 = var7.length;
            for (int var10 = 0; var10 < var9; ++var10) {
                final String var11 = var8[var10];
                ++var6;
                var5 = var11.length();
                var3 = var3 + var11;
            }
        } else {
            while (args[var4] instanceof String) {
                final String var13 = (String) args[var4++];
                ++var6;
                var5 = var13.length();
                var3 = var3 + var13;
            }
        }
        HashMap<Character, ItemStack> var14;
        for (var14 = new HashMap<Character, ItemStack>(); var4 < args.length; var4 += 2) {
            final Character var16 = (Character) args[var4];
            ItemStack var17 = null;
            if (args[var4 + 1] instanceof org.bukkit.inventory.ItemStack) {
                var17 = CraftItemStack.asNMSCopy((org.bukkit.inventory.ItemStack) args[var4 + 1]).cloneItemStack();
            } else if (args[var4 + 1] instanceof org.bukkit.Material) {
                var17 = CraftItemStack.asNMSCopy(new org.bukkit.inventory.ItemStack((Material) args[var4 + 1]));
            }
            var14.put(var16, var17);
        }
        final ItemStack[] var15 = new ItemStack[var5 * var6];
        for (var9 = 0; var9 < (var5 * var6); ++var9) {
            final char var18 = var3.charAt(var9);
            if (var14.containsKey(Character.valueOf(var18))) {
                var15[var9] = var14.get(Character.valueOf(var18)).cloneItemStack();
            } else {
                var15[var9] = null;
            }
        }
        final CustomShapedRecipe result = new CustomShapedRecipe(var5, var6, var15, item, name);
        CraftingManager.getInstance().recipes.add(result);
        CraftingManager.getInstance().sort();
        return result;
    }

    /** The result. */
    private final ItemStack result;
    /** The name. */
    private final String name;

    /**
     * Instantiates a new custom shaped recipe.
     * 
     * @param i the i
     * @param j the j
     * @param items the items
     * @param item the item
     * @param name the name
     */
    public CustomShapedRecipe(final int i, final int j, final ItemStack[] items, final ItemStack item, final String name) {
        super(i, j, items, item);
        result = item;
        this.name = name;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * net.minecraft.server.v1_8_R1.ShapedRecipes#a(net.minecraft.server.v1_8_R1
     * .InventoryCrafting)
     */
    @Override
    public ItemStack a(final InventoryCrafting inv) {
        ItemStack item = result.cloneItemStack();
        final org.bukkit.inventory.ItemStack[] inventory = new org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack[inv.getSize()];
        for (int i = 0; i < inv.getContents().length; i++) {
            inventory[i] = CraftItemStack.asBukkitCopy(inv.getContents()[i]);
        }
        if (result.getTag() != null) {
            item.setTag((NBTTagCompound) result.getTag().clone());
        }
        final PrepareRecipeEvent event = new PrepareRecipeEvent(inventory, CraftItemStack.asBukkitCopy(item), name);
        Bukkit.getPluginManager().callEvent(event);
        item = CraftItemStack.asNMSCopy(event.getResult());
        return item;
    }

    /**
     * Gets the name.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }
}
