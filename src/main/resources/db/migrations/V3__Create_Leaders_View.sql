-- V3 - Create Leaders View
CREATE VIEW leaders AS
SELECT
    RANK() OVER (ORDER BY COALESCE(SUM(g.hits), 0) DESC) AS rank,
    u.id AS id,
    u.username AS name,
    COALESCE(SUM(g.hits), 0) AS hits
FROM users u LEFT JOIN games g ON u.id = g.user_id
GROUP BY u.id, u.username
ORDER BY rank;