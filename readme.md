# GPPurgeAddon

A Spigot plugin that adds commands that allow a player with OP to start a purge, which ignores all GriefPrevention claims globally and allows for chaos!

`/claimpurgetoggle` - Toggles a purge to be activated or deactivated. Will be activated or deactivated permanently until the command is ran again. Cannot be run when `/claimpurgetimed` is active.

`/claimpurgetimed` - Allows you to start a purge for a set amount of seconds. Default is `60`. Cannot be run when a purge is active with `/claimpurgetoggle`.

*Note: Permissions will be reset for player's claims after the purge. This is due to the ability for players to change permissions for non-admin claims during the purge. You can disable this if wanted in the config file.*

Make sure to have the `GriefPrevention` plugin installed, or else it will not work! - https://github.com/GriefPrevention/GriefPrevention/