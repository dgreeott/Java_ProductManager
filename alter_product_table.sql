USE mma;

ALTER TABLE Product ADD Note TEXT;

UPDATE Product
SET Note = ''
WHERE ProductID < 1;