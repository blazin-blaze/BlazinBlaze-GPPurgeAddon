# GPPurgeAddon

A Spigot plugin that adds commands that allow a player with OP to start a purge, which ignores all claims globally and allows for chaos!

`/claimpurgetoggle` - Toggles a purge to be activated or deactivated. Will be activated or deactivated permanently until the command is ran again. Cannot be run when `/claimpurgetimed` is active.

`/claimpurgetimed` - Allows you to start a purge for a set amount of seconds. Default is `60`. Cannot be run when a purge is active with `/claimpurgetoggle`.

Make sure to have the `GriefPrevention` plugin installed, or else it will not work! - https://github.com/GriefPrevention/GriefPrevention/