package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.Symbol
import com.example.ui.components.ReelBox
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun GameScreen() {
    // Game State Variables
    var playerHp by remember { mutableStateOf(100) }
    var bossHp by remember { mutableStateOf(150) }
    var playerCoins by remember { mutableStateOf(20) }
    var isSpinning by remember { mutableStateOf(false) }

    var reel1 by remember { mutableStateOf(Symbol.SWORD) }
    var reel2 by remember { mutableStateOf(Symbol.SHIELD) }
    var reel3 by remember { mutableStateOf(Symbol.COIN) }

    var cloverIntercomText by remember {
        mutableStateOf("CLOVER: 'Spin those reels, fresh meat! The House is waiting!'")
    }

    var microchipActive by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    // Main Dark/Grim Pipeline Theme Colors
    val darkRust = Color(0xFF1E1112)
    val neonGreen = Color(0xFF39FF14)
    val crimsonRed = Color(0xFF8B0000)
    val metallicGold = Color(0xFFFFD700)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(darkRust)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // TOP HEADER: Clover Intercom Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, neonGreen, RoundedCornerShape(8.dp))
                .background(Color.Black)
                .padding(12.dp)
        ) {
            Text(
                text = cloverIntercomText,
                color = neonGreen,
                fontSize = 14.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            )
        }

        // BOSS DISPLAY
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF2A2A2A))
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("BOSS MACHINE: MECHANICAL CELL #01", color = Color.White, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = { bossHp / 150f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp),
                    color = crimsonRed,
                    trackColor = Color.DarkGray,
                )
                Text("HP: $bossHp / 150", color = Color.LightGray, fontSize = 12.sp)
            }
        }

        // SLOT MACHINE REELS (3-Reel System)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black, RoundedCornerShape(12.dp))
                .border(3.dp, metallicGold, RoundedCornerShape(12.dp))
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ReelBox(symbol = reel1, isSpinning = isSpinning)
            ReelBox(symbol = reel2, isSpinning = isSpinning)
            ReelBox(symbol = reel3, isSpinning = isSpinning)
        }

        // PLAYER STATS
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("PLAYER HP: $playerHp/100", color = Color.Red, fontWeight = FontWeight.Bold)
            Text("COINS: 🪙 $playerCoins", color = metallicGold, fontWeight = FontWeight.Bold)
        }

        // PCB HARDWARE CHIP TOGGLE
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF152215))
        ) {
            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("PCB CHIP: NOR-7 Logic Gate", color = neonGreen, fontWeight = FontWeight.Bold)
                    Text("Converts Skulls into Swords", color = Color.Gray, fontSize = 11.sp)
                }
                Switch(
                    checked = microchipActive,
                    onCheckedChange = { microchipActive = it },
                    colors = SwitchDefaults.colors(checkedThumbColor = neonGreen)
                )
            }
        }

        // ACTION BUTTONS
        Button(
            onClick = {
                if (!isSpinning && playerHp > 0 && bossHp > 0) {
                    isSpinning = true
                    coroutineScope.launch {
                        // Simulate Reel Spin Animation Delays
                        repeat(10) {
                            reel1 = Symbol.values().random()
                            reel2 = Symbol.values().random()
                            reel3 = Symbol.values().random()
                            delay(80)
                        }

                        // Apply PCB Microchip Hardware logic override
                        if (microchipActive) {
                            if (reel1 == Symbol.SKULL) reel1 = Symbol.SWORD
                            if (reel2 == Symbol.SKULL) reel2 = Symbol.SWORD
                            if (reel3 == Symbol.SKULL) reel3 = Symbol.SWORD
                        }

                        isSpinning = false

                        // Calculate Reel Combat Outcomes
                        var totalDamage = reel1.baseDamage + reel2.baseDamage + reel3.baseDamage
                        val totalShield = reel1.baseShield + reel2.baseShield + reel3.baseShield
                        val gainedCoins = reel1.coins + reel2.coins + reel3.coins

                        // 3-of-a-kind Triple Jackpot Bonus
                        if (reel1 == reel2 && reel2 == reel3) {
                            totalDamage *= 2
                            cloverIntercomText = "CLOVER: 'TRIPLE MATCH! Don't get used to winning, rodent!'"
                        } else {
                            cloverIntercomText = "CLOVER: 'Spin again! Every tick of the clock costs you life!'"
                        }

                        // Update State Values
                        bossHp = (bossHp - totalDamage).coerceAtLeast(0)
                        playerCoins += gainedCoins

                        // Boss Retaliation Attack
                        val bossAttack = Random.nextInt(8, 18)
                        val damageTaken = (bossAttack - totalShield).coerceAtLeast(0)
                        playerHp = (playerHp - damageTaken).coerceAtLeast(0)

                        if (bossHp == 0) {
                            cloverIntercomText = "CLOVER: 'Chamber Boss Destroyed! Deposit Tariff at the ATM to advance!'"
                        } else if (playerHp == 0) {
                            cloverIntercomText = "CLOVER: 'GAME OVER! Strip his bones and weld him into Machine #02!'"
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = crimsonRed),
            enabled = !isSpinning && playerHp > 0 && bossHp > 0
        ) {
            Text(
                text = if (isSpinning) "SPINNING..." else "PULL LEVER (SPIN)",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
