[![Circle CI](https://img.shields.io/circleci/project/github/lannablue89/SimpleChat.svg?style=flat-square)](https://circleci.com/gh/lannablue89/SimpleChat)


# SimpleChat

This is an app demo for a simple chat application. There is a demo that an chat flow between 2 users, one from dummy service that will auto generate message by the time, one for real user input as "Me" role.

Here I applied some nice things that seems hot recently.

- MVVM
- RxJava
- Unit Test

#### Supported Android version: 5.0+

## MVVM

The newest software architecture pattern that introduced for multi platform and also Android, at the time data binding was introduced, MVVM will help Android developer build an app in quick and easy to test. 

There is some main classes:

- Model: ChatMessage (just contain data as a chat message), ChatServiceModel (manage generate messages from friend), NotificationModel (handle the notification for the app)
- View: ChatDetailFragment and ChatService. The fragment will contain UI views and receive actions from user. In this case, it just contain the list of ChatMessages (RecycleView with linear layout in vertical). The service that keeps the list of messages and the process that the messages from friend role will be generated (via ChatServiceModel).
- ViewModel: the ChatViewModel contains artributes to map with UI main content, this case, there is list of ChatMessages which will be presented in UI.

## RxJava

The ChatServiceModel: the hero that help generate random message each 3s.

```sh
Observable.zip(
                Observable.range(1, 15).map(n -> randomString(n * 30)),
                Observable.interval(3000, TimeUnit.MILLISECONDS),
                (obs, timer) -> (System.currentTimeMillis() - startTime) + "ms: " + obs
        ).repeat()
        .subscribe(subscriber);
```

I also have PublishSubject as a observable bridge that it receives the messages was sent from ChatService and notify to observers which subscripted to it, the ChatViewModel. For case app in background, service will call to NotificationModel to generate the notification that prompt messages to user.


## Unit Test
So amazing that how MVVM help us easy to write the unit test, basicly I write test for there two ViewModel, there is ChatViewModel and ItemChatViewModel to make sure the logic and UI values.


# Creator:
Lanna Blue
