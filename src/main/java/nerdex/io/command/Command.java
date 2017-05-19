package nerdex.io.command;/*
 * SpigotUtils
 * Copyright (C) 2017 RapidTheNerd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@SuppressWarnings("unused")
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

public @interface Command {


        String name() default ""; // Command main name.

        String permission() default ""; // Command would only run if the sender has this permission.

        String arguments() default ""; // Command would only run if the arguments are met, with the exception of the wildcards symbol ( * ).

        String permissionError() default "default"; // Insufficient Permission Error.

        String argumentsError() default "default"; // Invalid arguments Error.

        String playerError() default "default";  // Sender must be a player Error.

    }

