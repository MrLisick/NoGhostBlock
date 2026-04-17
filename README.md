NoGhostBlock (Fabric)
NoGhostBlock is a lightweight, client-side utility mod designed to eliminate one of the most frustrating issues in multiplayer Minecraft: "ghost blocks."

🛠 What problem does this solve?
When playing on servers with high latency or using high-speed mining tools, your game client often thinks a block has been broken while the server disagrees. This results in an invisible, "ghost" block that prevents you from moving or placing new blocks.

NoGhostBlock fixes this using two primary methods:

Automatic Resync: Every time you stop mining a block, the mod sends an ABORT_DESTROY_BLOCK packet to the server. This forces the server to send back the true state of that block, instantly correcting any mismatch.

Manual Refresh Command: If you encounter existing ghost blocks around you, simply type /ghost. The mod will scan a 9x9x9 area around your character and force a block update for every position, making phantom blocks reappear or disappear as they should.

🚀 Key Features:
Purely Client-Side: No server-side installation required. Works on vanilla servers, realms, and large multiplayer networks.

Performance Focused: Built with Java 21 and Mixin for maximum efficiency and minimal impact on your FPS.

Command Support: Easy-to-use /ghost command registered via the Fabric Command API.
