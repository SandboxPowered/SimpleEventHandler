**Simple Event Handler**:
A system inspired by the C# event system.

Feel free to use or modify this anywhere.

**Usage:**
You just need an instance of EventHandler<S, A>, with S being the type of sender and A being the type of arguments(which extends EventArgs, so you could include data to your event).
Anyone that can access said instance can subscribe or unsubscribe from the event, and the event can be posted using the accept(sender, args) method.
