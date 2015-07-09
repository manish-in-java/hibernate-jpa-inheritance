package org.example.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Represents a leadership ability card assigned to a player
 * within a game.
 */
@Entity
@DiscriminatorValue("leader")
public class LeaderCardInstance extends CardInstance<LeaderCard>
{
  /**
   * Deliberately hidden to prevent direct instantiation.
   */
  LeaderCardInstance()
  {
    super();
  }
}
