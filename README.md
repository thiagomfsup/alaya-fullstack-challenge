
# Alaya mern dev challenge 

## Presentation

In this project which is a MERN stack you can write and edit post blog.

This application is composed by 2 repositories, the server (Spring Boot app) and the client (React):

- In the `blog-server` repository you can find the Spring Boot app server that connect to a local mongo database and expose
the api.
To start the server be sure to have installed mongoDB locally as a service then go in the server repository and launch the app:
```$xslt
    cd blog-server
    mvn spring-boot:run
```

- In the `client` repository you have the Front-end code of the blog that uses React and Redux.
To start the Front-end
```
    cd client
    npm i
    npm start
```

## Show us your skills :)

Please create a pull request for each exercise, so that we can evaluate the final features' code.
Don't hesitate to enhance the setup documentation if required.

To show us your skills we would like you to build theses 2 features:

### 1 - User account management

At first, we want the users to be able to create their account to post Articles.

To do that you need to create a login page or dialog and a signup process.

We want you to use JWT to manage user session https://jwt.io/, to do that you can use passport with a JWT policy.

Only connected users will be able to create post, and only the author of the post will be able to delete it

### 2 - Picture upload

Here we want the user to be able to upload and manage pictures on his post.

Free to you to use the service you want to do that and to store your assets (like Cloudinary for example).

The purpose is to enhance post with media.

You can design the layout you want on cards and on each post page and use the styling you want.

Also, if you want to add one or more features or change the layout, styling and theming about the blog you can.

Good luck :)

_Notes: For evaluation, we will mainly focus on the code structure and readability, the separation of concerns in the methods/components, and the visual consistency._
