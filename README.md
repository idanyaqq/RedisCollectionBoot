https://github.com/Ecwid/new-job/blob/master/Redis-and-collections.md

Реализовано: добавлена структура `RedisMap` со всеми методами интерфейса `Map`

Сделать решил веб сервис на основе SpringBoot.
Задача по добавлению/удалению серверов (node как я понял) на лету достаточно интересная. Получилось сделать пока только так: в редис кластер добавляется новая нода,
и только после этого дергается endpoint для обновления `JedisCluster`. Можно попробовать реализовать все полностью через web приложение, но мне показалось, 
что на данном этапе для тестового это слишком ресурсозатратно.
