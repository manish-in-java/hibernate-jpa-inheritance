package org.example.model;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Integration tests for {@link Player}.
 */
public class PlayerTests
{
  /**
   * Tests that players can be loaded successfully.
   */
  @Test
  public void testGetAllPlayers()
  {
    final EntityManager entityManager = getEntityManager();
    final CriteriaQuery<Player> query = getEntityManager().getCriteriaBuilder().createQuery(Player.class);
    final Root<Player> root = query.from(Player.class);
    query.select(root);

    final List<Player> players = entityManager.createQuery(query).getResultList();

    assertNotNull(players);
    assertNotEquals(0, players.size());

    for (final Player player : players)
    {
      assertNotNull(player);
      assertNotNull(player.getCards());
      assertTrue(player.getCards().size() >= 0);
      assertNotNull(player.getHandle());
      assertNotNull(player.getID());

      for (final CardInstance<?> card : player.getCards())
      {
        assertNotNull(card);
        assertNotNull(card.getCard());
        assertNotNull(card.getCard().getID());
        assertNotNull(card.getCard().getType());
        assertNotNull(card.getID());
        assertNotNull(card.getPlayer());
        assertNotNull(card.getType());

        System.out.println(String.format("%3d %10s %3d %10s %20s %3d %10s %15s %3d"
            , player.getID()
            , player.getHandle()
            , card.getID()
            , card.getType()
            , card.getClass().getSimpleName()
            , card.getCard().getID()
            , card.getCard().getType()
            , card.getCard().getClass().getSimpleName()
            , card.getCard().getDuration()));
      }
    }
  }

  /**
   * Gets a JPA {@link EntityManager}.
   */
  private EntityManager getEntityManager()
  {
    return Persistence.createEntityManagerFactory("game").createEntityManager();
  }
}
