CREATE TABLE if not exists Content (
                         id UUID PRIMARY KEY,
                         tag VARCHAR(20) NOT NULL,
                         title VARCHAR(255) NOT NULL,
                         description VARCHAR(255) NOT NULL,
                         content TEXT NOT NULL,
                         background VARCHAR(255) NOT NULL,
                         avgSalary DOUBLE NOT NULL
);
INSERT INTO Content (id, tag, title, description, content, background, avgSalary)
    values
        ('7c9ca8b4-5510-4c97-a7c4-457bce671233', 'NEWS', 'Curso de Programação', 'Aprenda os conceitos básicos de programação', 'Este curso abrange conceitos fundamentais de programação.', 'background_image1.jpg', 1500.00),
        ('f7437e0b-15ea-4a9e-8cd1-21a3a34b654f
', 'BLOG', 'Fundamentos de Web Design', 'Explore os princípios do design web', 'Aprenda sobre UI/UX e crie websites visualmente atraentes.', 'background_image2.jpg', 1200.00),
        ('e8ab20cd-af2b-4c9e-bb18-0d63cf04f0e6
', 'POST', 'Essenciais de Ciência de Dados', 'Introdução à ciência de dados', 'Obtenha insights sobre análise de dados e técnicas de machine learning.', 'background_image3.jpg', 1800.00),
        ('3fd2e801-857f-4b8b-b9ac-619d4b1d309d
', 'UNDEFINED', 'Gestão Empresarial', 'Desenvolva habilidades de gestão de negócios', 'Este curso aborda tópicos como estratégia empresarial e liderança.', 'background_image4.jpg', 2000.00),
        ('52e62d79-8e69-42c3-99ec-b2b9682c3180
', 'LANGUAGES', 'Aprenda um Novo Idioma', 'Explore uma nova língua e cultura', 'Aprimore suas habilidades de comunicação em um novo idioma.', 'background_image5.jpg', 800.00),
        ('a3a5475a-7a3a-41f1-bf8c-2eaf4327f4e5
', 'MARKETING', 'Marketing Digital', 'Conceitos essenciais de marketing online', 'Descubra estratégias eficazes para promover produtos e serviços online.', 'background_image6.jpg', 1600.00),
        ('b9f92f4d-7a8e-4a3c-9804-71eeb708c8e0
', 'HEALTH', 'Bem-Estar e Saúde Mental', 'Cuide da sua saúde mental e bem-estar', 'Este curso aborda práticas para promover uma vida saudável e equilibrada.', 'background_image7.jpg', 1300.00),
        ('546174e4-36ad-4c7e-8b34-cf6e5eb3e4f7
', 'FINANCE', 'Fundamentos de Finanças Pessoais', 'Aprenda a gerenciar suas finanças', 'Descubra estratégias para economizar, investir e planejar o futuro financeiro.', 'background_image8.jpg', 1700.00),
        ('d847dd09-2f4c-4a79-8d05-3a34b0d53482
', 'PHOTOGRAPHY', 'Fotografia Básica', 'Princípios fundamentais de fotografia', 'Explore técnicas de composição e captura de imagens.', 'background_image9.jpg', 1100.00),
        ('1f30c59d-9657-45a9-bb63-c3d652977f38
', 'COOKING', 'Culinária para Iniciantes', 'Descubra o mundo da culinária', 'Aprenda receitas básicas e técnicas de cozinha.', 'background_image10.jpg', 900.00);

