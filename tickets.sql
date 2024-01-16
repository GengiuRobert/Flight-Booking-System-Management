PGDMP  +                     |            tickets_system    16.1    16.1 �    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    25313    tickets_system    DATABASE     �   CREATE DATABASE tickets_system WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1251';
    DROP DATABASE tickets_system;
                postgres    false            �            1259    25315    airlines    TABLE     �   CREATE TABLE public.airlines (
    id integer NOT NULL,
    code_of_name character varying(10) NOT NULL,
    name character varying(255) NOT NULL
);
    DROP TABLE public.airlines;
       public         heap    postgres    false            �            1259    25314    airlines_id_seq    SEQUENCE     �   CREATE SEQUENCE public.airlines_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.airlines_id_seq;
       public          postgres    false    216            �           0    0    airlines_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.airlines_id_seq OWNED BY public.airlines.id;
          public          postgres    false    215            �            1259    25441    airport_info    TABLE     �   CREATE TABLE public.airport_info (
    id integer NOT NULL,
    leave_airport character varying(255) NOT NULL,
    arrival_airport character varying(255) NOT NULL
);
     DROP TABLE public.airport_info;
       public         heap    postgres    false            �            1259    25440    airport_info_id_seq    SEQUENCE     �   CREATE SEQUENCE public.airport_info_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.airport_info_id_seq;
       public          postgres    false    228            �           0    0    airport_info_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.airport_info_id_seq OWNED BY public.airport_info.id;
          public          postgres    false    227            �            1259    25455 	   city_info    TABLE     �   CREATE TABLE public.city_info (
    id integer NOT NULL,
    leave_city character varying(255) NOT NULL,
    arrival_city character varying(255) NOT NULL
);
    DROP TABLE public.city_info;
       public         heap    postgres    false            �            1259    25454    city_info_id_seq    SEQUENCE     �   CREATE SEQUENCE public.city_info_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.city_info_id_seq;
       public          postgres    false    230            �           0    0    city_info_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.city_info_id_seq OWNED BY public.city_info.id;
          public          postgres    false    229            �            1259    25338    clients    TABLE     �   CREATE TABLE public.clients (
    id integer NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    balance_money integer NOT NULL
);
    DROP TABLE public.clients;
       public         heap    postgres    false            �            1259    25337    clients_id_seq    SEQUENCE     �   CREATE SEQUENCE public.clients_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.clients_id_seq;
       public          postgres    false    220            �           0    0    clients_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.clients_id_seq OWNED BY public.clients.id;
          public          postgres    false    219            �            1259    25385    credit_cards    TABLE       CREATE TABLE public.credit_cards (
    id integer NOT NULL,
    client_id integer,
    cvv character varying(4) NOT NULL,
    card_number character varying(16) NOT NULL,
    expiration_date character varying(12) NOT NULL,
    card_type character varying(20) NOT NULL
);
     DROP TABLE public.credit_cards;
       public         heap    postgres    false            �            1259    25384    credit_cards_id_seq    SEQUENCE     �   CREATE SEQUENCE public.credit_cards_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.credit_cards_id_seq;
       public          postgres    false    222            �           0    0    credit_cards_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.credit_cards_id_seq OWNED BY public.credit_cards.id;
          public          postgres    false    221            �            1259    25477 	   date_info    TABLE     �   CREATE TABLE public.date_info (
    id integer NOT NULL,
    leave_date character varying(10) NOT NULL,
    arrival_date character varying(10) NOT NULL
);
    DROP TABLE public.date_info;
       public         heap    postgres    false            �            1259    25476    date_info_id_seq    SEQUENCE     �   CREATE SEQUENCE public.date_info_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.date_info_id_seq;
       public          postgres    false    232            �           0    0    date_info_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.date_info_id_seq OWNED BY public.date_info.id;
          public          postgres    false    231            �            1259    25417 	   hour_info    TABLE     �   CREATE TABLE public.hour_info (
    id integer NOT NULL,
    leave_hour character varying(5) NOT NULL,
    arrival_hour character varying(5) NOT NULL
);
    DROP TABLE public.hour_info;
       public         heap    postgres    false            �            1259    25416    hour_info_id_seq    SEQUENCE     �   CREATE SEQUENCE public.hour_info_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.hour_info_id_seq;
       public          postgres    false    224            �           0    0    hour_info_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.hour_info_id_seq OWNED BY public.hour_info.id;
          public          postgres    false    223            �            1259    25725 
   plane_info    TABLE     �   CREATE TABLE public.plane_info (
    id integer NOT NULL,
    plane_name character varying(255) NOT NULL,
    total_seats integer NOT NULL,
    available_seats integer NOT NULL,
    second_class_seats integer,
    first_class_seats integer
);
    DROP TABLE public.plane_info;
       public         heap    postgres    false            �            1259    25724    plane_info_id_seq    SEQUENCE     �   CREATE SEQUENCE public.plane_info_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.plane_info_id_seq;
       public          postgres    false    238            �           0    0    plane_info_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.plane_info_id_seq OWNED BY public.plane_info.id;
          public          postgres    false    237            �            1259    25429 
   price_info    TABLE     �   CREATE TABLE public.price_info (
    id integer NOT NULL,
    first_class integer NOT NULL,
    second_class integer NOT NULL
);
    DROP TABLE public.price_info;
       public         heap    postgres    false            �            1259    25428    price_info_id_seq    SEQUENCE     �   CREATE SEQUENCE public.price_info_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.price_info_id_seq;
       public          postgres    false    226            �           0    0    price_info_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.price_info_id_seq OWNED BY public.price_info.id;
          public          postgres    false    225            �            1259    25642    review_info    TABLE     �   CREATE TABLE public.review_info (
    id integer NOT NULL,
    ticket_id integer,
    review character varying(255) DEFAULT 'No review added yet'::character varying NOT NULL,
    rating double precision DEFAULT 0,
    client_id integer
);
    DROP TABLE public.review_info;
       public         heap    postgres    false            �            1259    25641    review_info_id_seq    SEQUENCE     �   CREATE SEQUENCE public.review_info_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.review_info_id_seq;
       public          postgres    false    236            �           0    0    review_info_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.review_info_id_seq OWNED BY public.review_info.id;
          public          postgres    false    235            �            1259    25745 	   seat_info    TABLE     �   CREATE TABLE public.seat_info (
    id integer NOT NULL,
    seat_number integer,
    is_seat_reserved boolean DEFAULT false,
    ticket_id integer
);
    DROP TABLE public.seat_info;
       public         heap    postgres    false            �            1259    25744    seat_info_id_seq    SEQUENCE     �   CREATE SEQUENCE public.seat_info_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.seat_info_id_seq;
       public          postgres    false    240            �           0    0    seat_info_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.seat_info_id_seq OWNED BY public.seat_info.id;
          public          postgres    false    239            �            1259    25324    tickets    TABLE     �  CREATE TABLE public.tickets (
    id integer NOT NULL,
    company_id integer,
    leave_city_id integer,
    arrival_city_id integer,
    leave_date_id integer,
    arrival_date_id integer,
    first_class_id integer,
    second_class_id integer,
    leave_hour_id integer,
    arrival_hour_id integer,
    leave_airport_id integer,
    arrival_airport_id integer,
    plane_id integer
);
    DROP TABLE public.tickets;
       public         heap    postgres    false            �            1259    25323    tickets_id_seq    SEQUENCE     �   CREATE SEQUENCE public.tickets_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.tickets_id_seq;
       public          postgres    false    218            �           0    0    tickets_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.tickets_id_seq OWNED BY public.tickets.id;
          public          postgres    false    217            �            1259    25513    transactions    TABLE     �  CREATE TABLE public.transactions (
    id integer NOT NULL,
    client_id integer,
    id_of_ticket integer,
    price integer DEFAULT 0 NOT NULL,
    type character varying(255) DEFAULT ''::character varying NOT NULL,
    leave_date character varying(255) DEFAULT ''::character varying NOT NULL,
    arrival_date character varying(255) DEFAULT ''::character varying NOT NULL,
    leave_city character varying(255) DEFAULT ''::character varying NOT NULL,
    arrival_city character varying(255) DEFAULT ''::character varying NOT NULL,
    leave_time character varying(5) DEFAULT ''::character varying NOT NULL,
    arrival_time character varying(5) DEFAULT ''::character varying NOT NULL,
    seat_number integer,
    is_seat_reserved boolean DEFAULT false
);
     DROP TABLE public.transactions;
       public         heap    postgres    false            �            1259    25512    transactions_id_seq    SEQUENCE     �   CREATE SEQUENCE public.transactions_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.transactions_id_seq;
       public          postgres    false    234            �           0    0    transactions_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.transactions_id_seq OWNED BY public.transactions.id;
          public          postgres    false    233            �           2604    25318    airlines id    DEFAULT     j   ALTER TABLE ONLY public.airlines ALTER COLUMN id SET DEFAULT nextval('public.airlines_id_seq'::regclass);
 :   ALTER TABLE public.airlines ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    216    216            �           2604    25444    airport_info id    DEFAULT     r   ALTER TABLE ONLY public.airport_info ALTER COLUMN id SET DEFAULT nextval('public.airport_info_id_seq'::regclass);
 >   ALTER TABLE public.airport_info ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    227    228    228            �           2604    25458    city_info id    DEFAULT     l   ALTER TABLE ONLY public.city_info ALTER COLUMN id SET DEFAULT nextval('public.city_info_id_seq'::regclass);
 ;   ALTER TABLE public.city_info ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    229    230    230            �           2604    25341 
   clients id    DEFAULT     h   ALTER TABLE ONLY public.clients ALTER COLUMN id SET DEFAULT nextval('public.clients_id_seq'::regclass);
 9   ALTER TABLE public.clients ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    220    219    220            �           2604    25388    credit_cards id    DEFAULT     r   ALTER TABLE ONLY public.credit_cards ALTER COLUMN id SET DEFAULT nextval('public.credit_cards_id_seq'::regclass);
 >   ALTER TABLE public.credit_cards ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    222    221    222            �           2604    25480    date_info id    DEFAULT     l   ALTER TABLE ONLY public.date_info ALTER COLUMN id SET DEFAULT nextval('public.date_info_id_seq'::regclass);
 ;   ALTER TABLE public.date_info ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    232    231    232            �           2604    25420    hour_info id    DEFAULT     l   ALTER TABLE ONLY public.hour_info ALTER COLUMN id SET DEFAULT nextval('public.hour_info_id_seq'::regclass);
 ;   ALTER TABLE public.hour_info ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    223    224    224            �           2604    25728    plane_info id    DEFAULT     n   ALTER TABLE ONLY public.plane_info ALTER COLUMN id SET DEFAULT nextval('public.plane_info_id_seq'::regclass);
 <   ALTER TABLE public.plane_info ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    237    238    238            �           2604    25432    price_info id    DEFAULT     n   ALTER TABLE ONLY public.price_info ALTER COLUMN id SET DEFAULT nextval('public.price_info_id_seq'::regclass);
 <   ALTER TABLE public.price_info ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    225    226    226            �           2604    25645    review_info id    DEFAULT     p   ALTER TABLE ONLY public.review_info ALTER COLUMN id SET DEFAULT nextval('public.review_info_id_seq'::regclass);
 =   ALTER TABLE public.review_info ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    235    236    236            �           2604    25748    seat_info id    DEFAULT     l   ALTER TABLE ONLY public.seat_info ALTER COLUMN id SET DEFAULT nextval('public.seat_info_id_seq'::regclass);
 ;   ALTER TABLE public.seat_info ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    239    240    240            �           2604    25327 
   tickets id    DEFAULT     h   ALTER TABLE ONLY public.tickets ALTER COLUMN id SET DEFAULT nextval('public.tickets_id_seq'::regclass);
 9   ALTER TABLE public.tickets ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    217    218            �           2604    25516    transactions id    DEFAULT     r   ALTER TABLE ONLY public.transactions ALTER COLUMN id SET DEFAULT nextval('public.transactions_id_seq'::regclass);
 >   ALTER TABLE public.transactions ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    234    233    234            �          0    25315    airlines 
   TABLE DATA           :   COPY public.airlines (id, code_of_name, name) FROM stdin;
    public          postgres    false    216   ��       �          0    25441    airport_info 
   TABLE DATA           J   COPY public.airport_info (id, leave_airport, arrival_airport) FROM stdin;
    public          postgres    false    228   V�       �          0    25455 	   city_info 
   TABLE DATA           A   COPY public.city_info (id, leave_city, arrival_city) FROM stdin;
    public          postgres    false    230   ٧       �          0    25338    clients 
   TABLE DATA           K   COPY public.clients (id, email, password, name, balance_money) FROM stdin;
    public          postgres    false    220   6�       �          0    25385    credit_cards 
   TABLE DATA           c   COPY public.credit_cards (id, client_id, cvv, card_number, expiration_date, card_type) FROM stdin;
    public          postgres    false    222   ��       �          0    25477 	   date_info 
   TABLE DATA           A   COPY public.date_info (id, leave_date, arrival_date) FROM stdin;
    public          postgres    false    232   ɨ       �          0    25417 	   hour_info 
   TABLE DATA           A   COPY public.hour_info (id, leave_hour, arrival_hour) FROM stdin;
    public          postgres    false    224   �       �          0    25725 
   plane_info 
   TABLE DATA           y   COPY public.plane_info (id, plane_name, total_seats, available_seats, second_class_seats, first_class_seats) FROM stdin;
    public          postgres    false    238   R�       �          0    25429 
   price_info 
   TABLE DATA           C   COPY public.price_info (id, first_class, second_class) FROM stdin;
    public          postgres    false    226   ��       �          0    25642    review_info 
   TABLE DATA           O   COPY public.review_info (id, ticket_id, review, rating, client_id) FROM stdin;
    public          postgres    false    236   ��       �          0    25745 	   seat_info 
   TABLE DATA           Q   COPY public.seat_info (id, seat_number, is_seat_reserved, ticket_id) FROM stdin;
    public          postgres    false    240   �       �          0    25324    tickets 
   TABLE DATA           �   COPY public.tickets (id, company_id, leave_city_id, arrival_city_id, leave_date_id, arrival_date_id, first_class_id, second_class_id, leave_hour_id, arrival_hour_id, leave_airport_id, arrival_airport_id, plane_id) FROM stdin;
    public          postgres    false    218   U�       �          0    25513    transactions 
   TABLE DATA           �   COPY public.transactions (id, client_id, id_of_ticket, price, type, leave_date, arrival_date, leave_city, arrival_city, leave_time, arrival_time, seat_number, is_seat_reserved) FROM stdin;
    public          postgres    false    234   ��       �           0    0    airlines_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.airlines_id_seq', 9, true);
          public          postgres    false    215            �           0    0    airport_info_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.airport_info_id_seq', 4, true);
          public          postgres    false    227            �           0    0    city_info_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.city_info_id_seq', 8, true);
          public          postgres    false    229            �           0    0    clients_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.clients_id_seq', 25, true);
          public          postgres    false    219            �           0    0    credit_cards_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.credit_cards_id_seq', 20, true);
          public          postgres    false    221            �           0    0    date_info_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.date_info_id_seq', 6, true);
          public          postgres    false    231            �           0    0    hour_info_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.hour_info_id_seq', 7, true);
          public          postgres    false    223            �           0    0    plane_info_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.plane_info_id_seq', 5, true);
          public          postgres    false    237            �           0    0    price_info_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.price_info_id_seq', 7, true);
          public          postgres    false    225            �           0    0    review_info_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.review_info_id_seq', 33, true);
          public          postgres    false    235            �           0    0    seat_info_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.seat_info_id_seq', 8, true);
          public          postgres    false    239            �           0    0    tickets_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.tickets_id_seq', 18, true);
          public          postgres    false    217            �           0    0    transactions_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.transactions_id_seq', 37, true);
          public          postgres    false    233            �           2606    25320    airlines airlines_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.airlines
    ADD CONSTRAINT airlines_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.airlines DROP CONSTRAINT airlines_pkey;
       public            postgres    false    216            �           2606    25448    airport_info airport_info_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.airport_info
    ADD CONSTRAINT airport_info_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.airport_info DROP CONSTRAINT airport_info_pkey;
       public            postgres    false    228            �           2606    25462    city_info city_info_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.city_info
    ADD CONSTRAINT city_info_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.city_info DROP CONSTRAINT city_info_pkey;
       public            postgres    false    230            �           2606    25347    clients clients_email_key 
   CONSTRAINT     U   ALTER TABLE ONLY public.clients
    ADD CONSTRAINT clients_email_key UNIQUE (email);
 C   ALTER TABLE ONLY public.clients DROP CONSTRAINT clients_email_key;
       public            postgres    false    220            �           2606    25345    clients clients_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.clients DROP CONSTRAINT clients_pkey;
       public            postgres    false    220            �           2606    25390    credit_cards credit_cards_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.credit_cards
    ADD CONSTRAINT credit_cards_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.credit_cards DROP CONSTRAINT credit_cards_pkey;
       public            postgres    false    222            �           2606    25482    date_info date_info_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.date_info
    ADD CONSTRAINT date_info_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.date_info DROP CONSTRAINT date_info_pkey;
       public            postgres    false    232            �           2606    25422    hour_info hour_info_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.hour_info
    ADD CONSTRAINT hour_info_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.hour_info DROP CONSTRAINT hour_info_pkey;
       public            postgres    false    224            �           2606    25730    plane_info plane_info_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.plane_info
    ADD CONSTRAINT plane_info_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.plane_info DROP CONSTRAINT plane_info_pkey;
       public            postgres    false    238            �           2606    25434    price_info price_info_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.price_info
    ADD CONSTRAINT price_info_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.price_info DROP CONSTRAINT price_info_pkey;
       public            postgres    false    226            �           2606    25649    review_info review_info_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.review_info
    ADD CONSTRAINT review_info_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.review_info DROP CONSTRAINT review_info_pkey;
       public            postgres    false    236            �           2606    25751    seat_info seat_info_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.seat_info
    ADD CONSTRAINT seat_info_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.seat_info DROP CONSTRAINT seat_info_pkey;
       public            postgres    false    240            �           2606    25331    tickets tickets_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.tickets DROP CONSTRAINT tickets_pkey;
       public            postgres    false    218            �           2606    25518    transactions transactions_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT transactions_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.transactions DROP CONSTRAINT transactions_pkey;
       public            postgres    false    234            �           2606    25778    airlines unique_airline_name 
   CONSTRAINT     W   ALTER TABLE ONLY public.airlines
    ADD CONSTRAINT unique_airline_name UNIQUE (name);
 F   ALTER TABLE ONLY public.airlines DROP CONSTRAINT unique_airline_name;
       public            postgres    false    216            �           2606    25784 #   airport_info unique_arrival_airport 
   CONSTRAINT     i   ALTER TABLE ONLY public.airport_info
    ADD CONSTRAINT unique_arrival_airport UNIQUE (arrival_airport);
 M   ALTER TABLE ONLY public.airport_info DROP CONSTRAINT unique_arrival_airport;
       public            postgres    false    228            �           2606    25772    city_info unique_arrival_city 
   CONSTRAINT     `   ALTER TABLE ONLY public.city_info
    ADD CONSTRAINT unique_arrival_city UNIQUE (arrival_city);
 G   ALTER TABLE ONLY public.city_info DROP CONSTRAINT unique_arrival_city;
       public            postgres    false    230            �           2606    25766    date_info unique_arrival_date 
   CONSTRAINT     `   ALTER TABLE ONLY public.date_info
    ADD CONSTRAINT unique_arrival_date UNIQUE (arrival_date);
 G   ALTER TABLE ONLY public.date_info DROP CONSTRAINT unique_arrival_date;
       public            postgres    false    232            �           2606    25796    hour_info unique_arrival_hour 
   CONSTRAINT     `   ALTER TABLE ONLY public.hour_info
    ADD CONSTRAINT unique_arrival_hour UNIQUE (arrival_hour);
 G   ALTER TABLE ONLY public.hour_info DROP CONSTRAINT unique_arrival_hour;
       public            postgres    false    224            �           2606    25415    credit_cards unique_card_number 
   CONSTRAINT     a   ALTER TABLE ONLY public.credit_cards
    ADD CONSTRAINT unique_card_number UNIQUE (card_number);
 I   ALTER TABLE ONLY public.credit_cards DROP CONSTRAINT unique_card_number;
       public            postgres    false    222            �           2606    25776    airlines unique_code_of_name 
   CONSTRAINT     _   ALTER TABLE ONLY public.airlines
    ADD CONSTRAINT unique_code_of_name UNIQUE (code_of_name);
 F   ALTER TABLE ONLY public.airlines DROP CONSTRAINT unique_code_of_name;
       public            postgres    false    216            �           2606    25780 )   airlines unique_code_of_name_airline_name 
   CONSTRAINT     r   ALTER TABLE ONLY public.airlines
    ADD CONSTRAINT unique_code_of_name_airline_name UNIQUE (code_of_name, name);
 S   ALTER TABLE ONLY public.airlines DROP CONSTRAINT unique_code_of_name_airline_name;
       public            postgres    false    216    216            �           2606    25788    price_info unique_first_class 
   CONSTRAINT     _   ALTER TABLE ONLY public.price_info
    ADD CONSTRAINT unique_first_class UNIQUE (first_class);
 G   ALTER TABLE ONLY public.price_info DROP CONSTRAINT unique_first_class;
       public            postgres    false    226            �           2606    25792 &   price_info unique_first_second_classes 
   CONSTRAINT     v   ALTER TABLE ONLY public.price_info
    ADD CONSTRAINT unique_first_second_classes UNIQUE (first_class, second_class);
 P   ALTER TABLE ONLY public.price_info DROP CONSTRAINT unique_first_second_classes;
       public            postgres    false    226    226            �           2606    25782 !   airport_info unique_leave_airport 
   CONSTRAINT     e   ALTER TABLE ONLY public.airport_info
    ADD CONSTRAINT unique_leave_airport UNIQUE (leave_airport);
 K   ALTER TABLE ONLY public.airport_info DROP CONSTRAINT unique_leave_airport;
       public            postgres    false    228            �           2606    25786 *   airport_info unique_leave_arrival_airports 
   CONSTRAINT        ALTER TABLE ONLY public.airport_info
    ADD CONSTRAINT unique_leave_arrival_airports UNIQUE (leave_airport, arrival_airport);
 T   ALTER TABLE ONLY public.airport_info DROP CONSTRAINT unique_leave_arrival_airports;
       public            postgres    false    228    228            �           2606    25774 %   city_info unique_leave_arrival_cities 
   CONSTRAINT     t   ALTER TABLE ONLY public.city_info
    ADD CONSTRAINT unique_leave_arrival_cities UNIQUE (leave_city, arrival_city);
 O   ALTER TABLE ONLY public.city_info DROP CONSTRAINT unique_leave_arrival_cities;
       public            postgres    false    230    230            �           2606    25768 $   date_info unique_leave_arrival_dates 
   CONSTRAINT     s   ALTER TABLE ONLY public.date_info
    ADD CONSTRAINT unique_leave_arrival_dates UNIQUE (leave_date, arrival_date);
 N   ALTER TABLE ONLY public.date_info DROP CONSTRAINT unique_leave_arrival_dates;
       public            postgres    false    232    232            �           2606    25798 $   hour_info unique_leave_arrival_hours 
   CONSTRAINT     s   ALTER TABLE ONLY public.hour_info
    ADD CONSTRAINT unique_leave_arrival_hours UNIQUE (leave_hour, arrival_hour);
 N   ALTER TABLE ONLY public.hour_info DROP CONSTRAINT unique_leave_arrival_hours;
       public            postgres    false    224    224            �           2606    25770    city_info unique_leave_city 
   CONSTRAINT     \   ALTER TABLE ONLY public.city_info
    ADD CONSTRAINT unique_leave_city UNIQUE (leave_city);
 E   ALTER TABLE ONLY public.city_info DROP CONSTRAINT unique_leave_city;
       public            postgres    false    230            �           2606    25764    date_info unique_leave_date 
   CONSTRAINT     \   ALTER TABLE ONLY public.date_info
    ADD CONSTRAINT unique_leave_date UNIQUE (leave_date);
 E   ALTER TABLE ONLY public.date_info DROP CONSTRAINT unique_leave_date;
       public            postgres    false    232            �           2606    25794    hour_info unique_leave_hour 
   CONSTRAINT     \   ALTER TABLE ONLY public.hour_info
    ADD CONSTRAINT unique_leave_hour UNIQUE (leave_hour);
 E   ALTER TABLE ONLY public.hour_info DROP CONSTRAINT unique_leave_hour;
       public            postgres    false    224            �           2606    25800    plane_info unique_plane_name 
   CONSTRAINT     ]   ALTER TABLE ONLY public.plane_info
    ADD CONSTRAINT unique_plane_name UNIQUE (plane_name);
 F   ALTER TABLE ONLY public.plane_info DROP CONSTRAINT unique_plane_name;
       public            postgres    false    238            �           2606    25790    price_info unique_second_class 
   CONSTRAINT     a   ALTER TABLE ONLY public.price_info
    ADD CONSTRAINT unique_second_class UNIQUE (second_class);
 H   ALTER TABLE ONLY public.price_info DROP CONSTRAINT unique_second_class;
       public            postgres    false    226            �           2606    25804    tickets unique_ticket 
   CONSTRAINT       ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT unique_ticket UNIQUE (company_id, leave_city_id, arrival_city_id, leave_date_id, arrival_date_id, first_class_id, second_class_id, leave_hour_id, arrival_hour_id, leave_airport_id, arrival_airport_id, plane_id);
 ?   ALTER TABLE ONLY public.tickets DROP CONSTRAINT unique_ticket;
       public            postgres    false    218    218    218    218    218    218    218    218    218    218    218    218            �           2606    25391 (   credit_cards credit_cards_client_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.credit_cards
    ADD CONSTRAINT credit_cards_client_id_fkey FOREIGN KEY (client_id) REFERENCES public.clients(id);
 R   ALTER TABLE ONLY public.credit_cards DROP CONSTRAINT credit_cards_client_id_fkey;
       public          postgres    false    220    222    4789            �           2606    25655 &   review_info review_info_client_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.review_info
    ADD CONSTRAINT review_info_client_id_fkey FOREIGN KEY (client_id) REFERENCES public.clients(id);
 P   ALTER TABLE ONLY public.review_info DROP CONSTRAINT review_info_client_id_fkey;
       public          postgres    false    236    220    4789            �           2606    25650 &   review_info review_info_ticket_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.review_info
    ADD CONSTRAINT review_info_ticket_id_fkey FOREIGN KEY (ticket_id) REFERENCES public.tickets(id);
 P   ALTER TABLE ONLY public.review_info DROP CONSTRAINT review_info_ticket_id_fkey;
       public          postgres    false    4783    236    218            �           2606    25758 "   seat_info seat_info_ticket_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.seat_info
    ADD CONSTRAINT seat_info_ticket_id_fkey FOREIGN KEY (ticket_id) REFERENCES public.tickets(id);
 L   ALTER TABLE ONLY public.seat_info DROP CONSTRAINT seat_info_ticket_id_fkey;
       public          postgres    false    240    4783    218            �           2606    25719 '   tickets tickets_arrival_airport_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_arrival_airport_id_fkey FOREIGN KEY (arrival_airport_id) REFERENCES public.airport_info(id);
 Q   ALTER TABLE ONLY public.tickets DROP CONSTRAINT tickets_arrival_airport_id_fkey;
       public          postgres    false    218    228    4811            �           2606    25679 $   tickets tickets_arrival_city_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_arrival_city_id_fkey FOREIGN KEY (arrival_city_id) REFERENCES public.city_info(id);
 N   ALTER TABLE ONLY public.tickets DROP CONSTRAINT tickets_arrival_city_id_fkey;
       public          postgres    false    4819    230    218            �           2606    25689 $   tickets tickets_arrival_date_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_arrival_date_id_fkey FOREIGN KEY (arrival_date_id) REFERENCES public.date_info(id);
 N   ALTER TABLE ONLY public.tickets DROP CONSTRAINT tickets_arrival_date_id_fkey;
       public          postgres    false    4827    232    218            �           2606    25709 $   tickets tickets_arrival_hour_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_arrival_hour_id_fkey FOREIGN KEY (arrival_hour_id) REFERENCES public.hour_info(id);
 N   ALTER TABLE ONLY public.tickets DROP CONSTRAINT tickets_arrival_hour_id_fkey;
       public          postgres    false    224    218    4795            �           2606    25332    tickets tickets_company_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_company_id_fkey FOREIGN KEY (company_id) REFERENCES public.airlines(id);
 I   ALTER TABLE ONLY public.tickets DROP CONSTRAINT tickets_company_id_fkey;
       public          postgres    false    4775    216    218            �           2606    25694 #   tickets tickets_first_class_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_first_class_id_fkey FOREIGN KEY (first_class_id) REFERENCES public.price_info(id);
 M   ALTER TABLE ONLY public.tickets DROP CONSTRAINT tickets_first_class_id_fkey;
       public          postgres    false    226    218    4803            �           2606    25714 %   tickets tickets_leave_airport_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_leave_airport_id_fkey FOREIGN KEY (leave_airport_id) REFERENCES public.airport_info(id);
 O   ALTER TABLE ONLY public.tickets DROP CONSTRAINT tickets_leave_airport_id_fkey;
       public          postgres    false    228    4811    218            �           2606    25674 "   tickets tickets_leave_city_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_leave_city_id_fkey FOREIGN KEY (leave_city_id) REFERENCES public.city_info(id);
 L   ALTER TABLE ONLY public.tickets DROP CONSTRAINT tickets_leave_city_id_fkey;
       public          postgres    false    230    4819    218            �           2606    25684 "   tickets tickets_leave_date_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_leave_date_id_fkey FOREIGN KEY (leave_date_id) REFERENCES public.date_info(id);
 L   ALTER TABLE ONLY public.tickets DROP CONSTRAINT tickets_leave_date_id_fkey;
       public          postgres    false    4827    232    218            �           2606    25704 "   tickets tickets_leave_hour_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_leave_hour_id_fkey FOREIGN KEY (leave_hour_id) REFERENCES public.hour_info(id);
 L   ALTER TABLE ONLY public.tickets DROP CONSTRAINT tickets_leave_hour_id_fkey;
       public          postgres    false    218    4795    224            �           2606    25731    tickets tickets_plane_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_plane_id_fkey FOREIGN KEY (plane_id) REFERENCES public.plane_info(id);
 G   ALTER TABLE ONLY public.tickets DROP CONSTRAINT tickets_plane_id_fkey;
       public          postgres    false    4839    218    238            �           2606    25699 $   tickets tickets_second_class_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_second_class_id_fkey FOREIGN KEY (second_class_id) REFERENCES public.price_info(id);
 N   ALTER TABLE ONLY public.tickets DROP CONSTRAINT tickets_second_class_id_fkey;
       public          postgres    false    226    4803    218            �           2606    25519 (   transactions transactions_client_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT transactions_client_id_fkey FOREIGN KEY (client_id) REFERENCES public.clients(id);
 R   ALTER TABLE ONLY public.transactions DROP CONSTRAINT transactions_client_id_fkey;
       public          postgres    false    4789    234    220            �           2606    25524 +   transactions transactions_id_of_ticket_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT transactions_id_of_ticket_fkey FOREIGN KEY (id_of_ticket) REFERENCES public.tickets(id);
 U   ALTER TABLE ONLY public.transactions DROP CONSTRAINT transactions_id_of_ticket_fkey;
       public          postgres    false    4783    218    234            �   �   x�=��
�0E��=E����v	t�֨�t��XK�"�}ӥ�9p�wUBq`'�^=�P)Tƾ�=;�ӣ����9�!�i�����(�5�i����Gi=�	I� �"׋h4�l;:C&����'/PI|a{��|Ԩ��=7D���0x      �   s   x��M
1�ur�\@��HdVE�|N�F3�d�^��6���N�~����:KQ9��+��k�7��5LRC-�s�%�xK�o`���%y-|��C�K7H��� ߖ���&      �   M   x�3�t*M.-J-.�����K)J�2�H,�,��K-W��/��2�tJ-����t)MJ��2��L,��t,I�K����� ���      �   F   x�32�,-*.uH�M���K����OJ-*142v��pp�r%�&'"��UA���f\1z\\\ o{
      �   -   x�3��42�44�#.#������1�gYfq"W� :�
�      �   3   x�3�4202�54�5��3��Ӕ��Abs!�#�\1z\\\ w�2      �   6   x�3�44�20�44�\��`H�ˈ�� �3�L@�&���fV&F\1z\\\ 8�	�      �   F   x����@�ji�@�pi�m����j(�@hp�D��X5T�b��>���%�®�E���&�B��n$?sAQ      �   =   x����0��0LC�t��?G��NR!,�p�b�+q�	*a��7u�\�x��q�C��      �      x������ � �      �   3   x�3�4�,�4�2�4�&���)��6�463�9�!"���`F� G	�      �   Q   x�u���0�V1��z��:P�9>��H�!�6������G'��hlnR^.��`�rݤJ�5����7��m����0      �      x������ � �     