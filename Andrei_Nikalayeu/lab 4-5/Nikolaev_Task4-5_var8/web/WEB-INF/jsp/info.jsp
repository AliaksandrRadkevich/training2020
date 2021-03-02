<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="property.messages" />

<fmt:message var="title" key="info.title"/>
<fmt:message var="serviceName" key="service.name"/>


<u:page title="${title} &#34;${serviceName}&#34;">
    <h2 class="text-center">Вариант 8.</h2>
    <p>
        <strong>Клиент</strong> заполняет <strong>заявку</strong>, указывая количество мест в номере, класс 
        апартаментов и время пребывания.
    </p> 
    <p>
        <strong>Администратор</strong> просматривает поступившую <strong>заявку</strong>, выделяет наиболее подходящий 
        из доступных <strong>номеров</strong>, после чего система выставляет <strong>счет Клиенту</strong>.
    </p>
    
    <h2 class="text-center">Требования к проекту</h2>
    
    <h3>Task 4 Разработать подсистему для работы с базой данных предложенной предметной области:</h3>
    <ol type="1">
        <li>
            <p>
                Разработать схему базы данных в соответствии с предметной областью вашего варианта. Создать sql-скрипты 
                создания БД, пользователя БД, создание таблиц, заполнение таблиц, удаление данных, удаление таблиц, 
                удаление БД, обновление данных, запросы на выборку данных.
            </p>
        </li>
        <li>
            <p>Информацию о предметной области хранить в БД, для доступа использовать API JDBC с 
                использованием пула соединений, разработанного самостоятельно. В качестве СУБД используется MySQL.
            </p>
        </li>
        <li>
            <p>
                На основе сущностей предметной области создать классы их описывающие.
            </p>
        </li>
        <li>
            <p>Классы и методы должны иметь отражающую их функциональность названия и должны быть 
                грамотно структурированы по пакетам.
            </p>
        </li>
        <li>
            <p>
                Оформление кода должно соответствовать Java Code Convention.
            </p>
        </li>
        <li>
            <p>
                Приложение должно поддерживать работу с кириллицей (быть многоязычной), в том числе и при хранении 
                информации в БД.
            </p>
        </li>
        <li>
            <p>
                Выполнить журналирование событий, то есть информацию о возникающих исключениях и событиях в системе 
                обрабатывать с помощью Log4j 2.
            </p>
        </li>
        <li>
            <p>
                Код должен содержать комментарии.
            </p>
        </li>
    </ol>
    
    <h3>Task 5 Построить веб-систему (для предметной области в соответствии с вариантом task 4), поддерживающую 
        заданную функциональность:</h3>
    <ol type="1">
        <li>
            <p>
                Интерфейс приложения должен поддерживать работу с кириллицей (быть многоязычной).
            </p>
        </li>
        <li>
            <p>
                Архитектура приложения должна соответствовать шаблону Model-View-Controller.
            </p>
        </li>
        <li>
            <p>
                При реализации алгоритмов бизнес-логики использовать шаблоны GoF: Factory Method, Command, Builder, 
                Strategy, State, Observer etc.
            </p>
        </li>
        <li>
            <p>
                Используя сервлеты и JSP, реализовать функциональности, предложенные в постановке 
                конкретной задачи.
            </p>
        </li>
        <li>
            <p>
                В страницах JSP применять библиотеку JSTL и разработать собственные теги.
            </p>
        </li>
        <li>
            <p>
                При разработке бизнес логики использовать сессии и фильтры.
            </p>
        </li>
        <li>
            <p>
                Код должен содержать комментарии.
            </p>
        </li>
    </ol>
</u:page>