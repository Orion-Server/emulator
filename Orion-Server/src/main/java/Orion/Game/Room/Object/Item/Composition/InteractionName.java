package Orion.Game.Room.Object.Item.Composition;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface InteractionName {
    String value();
}
