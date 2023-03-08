INSERT INTO CATEGORY(CATEGORY_ID, category_name) VALUES (1, 'Furniture');
INSERT INTO KEYWORD(keyword_id, keyword_name, category_id) VALUES (1, 'Root KW1', 1);
INSERT INTO KEYWORD(keyword_id, keyword_name, category_id) VALUES (2, 'Root KW2', 1);
INSERT INTO KEYWORD(keyword_id, keyword_name, category_id) VALUES (3, 'Root KW3', 1);

INSERT INTO CATEGORY(CATEGORY_ID, category_name) VALUES (2, 'Electronics');
INSERT INTO CATEGORY(CATEGORY_ID, category_name) VALUES (3, 'Home Appliances');

INSERT INTO CATEGORY(CATEGORY_ID, category_name, parent_Category_id) VALUES (4, 'Furniture-Child', 1);

INSERT INTO CATEGORY(CATEGORY_ID, category_name, parent_Category_id) VALUES (5, 'Electronics-Child', 2);
INSERT INTO KEYWORD(keyword_id, keyword_name, category_id) VALUES (4, 'Child KW1', 5);
INSERT INTO KEYWORD(keyword_id, keyword_name, category_id) VALUES (5, 'Child KW2', 5);
INSERT INTO KEYWORD(keyword_id, keyword_name, category_id) VALUES (6, 'Child KW3', 5);

INSERT INTO CATEGORY(CATEGORY_ID, category_name, parent_Category_id) VALUES (6, 'Furniture-Child-Child', 4);

