package com.locusintellect.martian.robots.commands;

import com.locusintellect.martian.robots.domain.FinalPosition;
import com.locusintellect.martian.robots.domain.Position;

public interface Movement {

    FinalPosition move(final Position position);
}
