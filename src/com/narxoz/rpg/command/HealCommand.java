package com.narxoz.rpg.command;

import com.narxoz.rpg.arena.ArenaFighter;

public class HealCommand implements ActionCommand {
    private final ArenaFighter target;
    private final int healAmount;
    private int actualHealApplied;

    public HealCommand(ArenaFighter target, int healAmount) {
        this.target = target;
        this.healAmount = healAmount;
    }

    @Override
    public void execute() {
        if (target.getHealPotions() <= 0) {
            System.out.println("[Heal] No potions left!");
            actualHealApplied = 0;
            return;
        }
        int before = target.getHealth();
        target.heal(healAmount);
        actualHealApplied = target.getHealth() - before;
        System.out.println("[Heal] Healed " + actualHealApplied + " HP");
    }

    @Override
    public void undo() {
        target.takeDamage(actualHealApplied);
    }

    @Override
    public String getDescription() {
        return "Heal for " + healAmount + " HP";
    }
}
