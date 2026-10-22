package com.example.data

enum class Symbol(val displayName: String, val icon: String, val baseDamage: Int, val baseShield: Int, val coins: Int) {
    SWORD("Sword", "⚔️", baseDamage = 15, baseShield = 0, coins = 0),
    SHIELD("Shield", "🛡️", baseDamage = 0, baseShield = 12, coins = 0),
    COIN("Coin", "🪙", baseDamage = 0, baseShield = 0, coins = 10),
    SKULL("Skull", "💀", baseDamage = 0, baseShield = 0, coins = 0), // Penalty symbol
    MAGIC("Magic", "🔮", baseDamage = 25, baseShield = 5, coins = 0)
}
