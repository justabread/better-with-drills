# Better With Drills

A mod to implement drills and other quality of life tools into Better Than Adventure for an immersive and expanded mining experience!

## Upcoming features list

### Functionality:
- [x] ~~Add drill tool~~
- [x] ~~Add drill tool materials instead of the quick to break pickaxe ones~~ 
- [x] ~~Add crafting recipes for drills~~
- [x] ~~Create functionality to switch between Tunnel (3x3) and OreMiner (Mine one block and destroy all connected ore blocks) modes~~
- [x] ~~Add chainsaw tool~~
- [x] ~~Expand drill tool to dirt and gravel too~~

### Design
- [x] ~~Create art for drills~~
  - [x] ~~Art for Iron Drill~~
  - [x] ~~Art for other drills~~
  - [x] ~~Replace placeholder pickaxe~~
- [x] ~~Create art for chainsaw~~

## Bugs
- ~~Drill doesn't get damaged when mining something it can't properly mine~~ (onBlockDestroyed now calls overridden parent function on condition failure)
- ~~Redstone does not trigger OreMine function~~ (It triggered the function, glowing redstone block ID-s were the issue)
- ~~OreMiner algorithm destroys order of insertion leading to unpredictable behavior~~ (Data structure used did not preserve order of insertion, so it has been switched to ArrayList for ease of element access and duplicates are now manually removed  
NOTE: investigate performance impact?)
