use tera_server;

-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Sam 21 Octobre 2017 à 11:24
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `tera_server`
--

-- --------------------------------------------------------

--
-- Structure de la table `accounts`
--

CREATE TABLE IF NOT EXISTS `accounts` (
  `AccountId` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(32) NOT NULL DEFAULT '',
  `password` varchar(256) CHARACTER SET latin1 DEFAULT '',
  `email` varchar(45) CHARACTER SET latin1 DEFAULT 'null@null',
  `access_level` smallint(6) NOT NULL DEFAULT '0',
  `end_pay` bigint(15) NOT NULL DEFAULT '0',
  `end_block` bigint(15) NOT NULL DEFAULT '0',
  `last_ip` varchar(15) NOT NULL DEFAULT '',
  `allow_ips` varchar(255) NOT NULL DEFAULT '*',
  `comments` varchar(255) NOT NULL DEFAULT '',
  `LastOnlineUtc` bigint(64) NOT NULL DEFAULT '0',
  `EmailVerify` varchar(256) NOT NULL,
  `PasswordRecovery` varchar(128) NOT NULL,
  `Coins` int(11) NOT NULL DEFAULT '0',
  `Ip` varchar(64) NOT NULL,
  `Membership` int(1) NOT NULL DEFAULT '0',
  `isGM` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`AccountId`),
  KEY `access_level` (`access_level`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `accounts`
--

-- --------------------------------------------------------

--
-- Structure de la table `account_bank`
--

CREATE TABLE IF NOT EXISTS `account_bank` (
  `account_name` varchar(45) NOT NULL,
  `bank_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`bank_id`),
  UNIQUE KEY `account_name_UNIQUE` (`account_name`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='ðóð░ð▒ð╗ð©Ðåð░ ð©ð┤ ð▒ð░ð¢ð║ð¥ð▓, ðÀð░ð║ÐÇðÁð┐ð╗ðÁð¢ð¢ÐïÐà ðÀð░ ð░ð║ð║ð░Ðâð¢Ðéð░ð╝ð©.' AUTO_INCREMENT=124693 ;

--
-- Contenu de la table `account_bank`
--

-- --------------------------------------------------------

--
-- Structure de la table `boss_spawn`
--

CREATE TABLE IF NOT EXISTS `boss_spawn` (
  `npc_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'ðÿð┤ ÐéðÁð╝ð┐ð╗ðÁð╣Ðéð░ ð¢ð┐Ðü.',
  `npc_type` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'ðóð©ð┐ ÐéðÁð╝ð┐ð╗ðÁð╣Ðéð░ ð¢ð┐Ðü.',
  `spawn` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT 'ðÆÐÇðÁð╝ÐÅ ðÀð░ð▓ðÁÐÇÐêðÁð¢ð©ÐÅ Ðüð┐ð░ð▓ð¢ð░.',
  PRIMARY KEY (`npc_id`,`npc_type`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='ðóð░ð▒ð╗ð©Ðåð░ ÐÇðÁÐüð┐ð░ð▓ð¢ð¥ð▓ ðáðæ.';

-- --------------------------------------------------------

--
-- Structure de la table `characters`
--

CREATE TABLE IF NOT EXISTS `characters` (
  `account_name` varchar(45) NOT NULL DEFAULT '',
  `object_id` int(11) NOT NULL DEFAULT '0',
  `class_id` tinyint(3) NOT NULL DEFAULT '0',
  `race_id` tinyint(1) NOT NULL DEFAULT '0',
  `sex` tinyint(1) NOT NULL DEFAULT '0',
  `char_name` varchar(35) NOT NULL DEFAULT '',
  `heading` int(15) NOT NULL DEFAULT '0',
  `online_time` bigint(15) NOT NULL DEFAULT '0',
  `create_time` bigint(15) NOT NULL DEFAULT '0',
  `end_ban` bigint(15) NOT NULL DEFAULT '0',
  `end_chat_ban` bigint(15) NOT NULL DEFAULT '0',
  `title` varchar(16) NOT NULL DEFAULT '',
  `guild_id` int(15) NOT NULL DEFAULT '0' COMMENT 'ðÿð┤ ð║ð╗ð░ð¢ð░ ð©ð│ÐÇð¥ð║ð░.',
  `access_level` tinyint(4) NOT NULL DEFAULT '0',
  `level` tinyint(3) NOT NULL DEFAULT '0',
  `exp` bigint(15) NOT NULL DEFAULT '0',
  `hp` int(11) NOT NULL DEFAULT '0',
  `mp` int(11) NOT NULL DEFAULT '0',
  `x` double(11,2) DEFAULT '0.00',
  `y` double(11,2) DEFAULT '0.00',
  `z` double(11,2) DEFAULT '0.00',
  `heart` smallint(3) NOT NULL DEFAULT '0',
  `attack_counter` tinyint(3) NOT NULL DEFAULT '0',
  `pvp_count` int(11) NOT NULL DEFAULT '0',
  `pve_count` int(11) NOT NULL DEFAULT '0',
  `guild_rank` tinyint(3) NOT NULL DEFAULT '0',
  `zone_id` int(10) unsigned NOT NULL DEFAULT '0',
  `guild_note` varchar(35) NOT NULL DEFAULT '' COMMENT 'ðáÔÇöðá┬░ðáÐÿðá┬ÁðíÔÇÜðáÐöðá┬░ ðáÐò ðáÐæðáÐûðíðéðáÐòðáÐöðá┬Á ðáÊæðá┬╗ðíðÅ ðáÐûðáÐæðá┬╗ðáÊæ ðá┬╗ðáÐæðíðâðíÔÇÜðá┬░.',
  `karma` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'ðÜð░ÐÇð╝ð░ ð©ð│ÐÇð¥ð║ð░.',
  `collect_mining` int(10) unsigned NOT NULL DEFAULT '1' COMMENT 'ðáÐ£ðá┬░ðáðåðíÔÇ╣ðáÐö ðíðâðá┬▒ðáÐòðíðéðá┬░ ðáÐöðá┬░ðáÐÿðáðàðá┬ÁðáÔäû.',
  `collect_plant` int(10) unsigned NOT NULL DEFAULT '1' COMMENT 'ðáÐ£ðá┬░ðáðåðíÔÇ╣ðáÐö ðíðâðá┬▒ðáÐòðíðéðá┬░ ðíðéðá┬░ðíðâðíÔÇÜðá┬ÁðáðàðáÐæðáÔäû.',
  `collect_energy` int(10) unsigned NOT NULL DEFAULT '1' COMMENT 'ðáÐ£ðá┬░ðáðåðíÔÇ╣ðíðâðáÐö ðíðâðá┬▒ðáÐòðíðéðá┬░ ðáÐöðíðéðáÐæðíðâðíÔÇÜðá┬░ðá┬╗ðáÐòðáðå.',
  `last_online` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'ðÆÐÇðÁð╝ÐÅ ð┐ð¥Ðüð╗ðÁð┤ð¢ðÁð│ð¥ ð¥ð¢ð╗ð░ð╣ð¢ð░ ð©ð│ÐÇð¥ð║ð░.',
  `continent_id` smallint(3) unsigned NOT NULL DEFAULT '0' COMMENT 'ðÿð┤ ð║ð¥ð¢Ðéð©ð¢ðÁð¢Ðéð░, ð¢ð░ ð║ð¥Ðéð¥ÐÇð¥ð╝ ð¢ð░Ðàð¥ð┤ð©ÐéÐüÐÅ ð©ð│ÐÇð¥ð║.',
  PRIMARY KEY (`object_id`),
  UNIQUE KEY `char_name` (`char_name`),
  KEY `account_name` (`account_name`),
  KEY `guild` (`guild_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


-- --------------------------------------------------------

--
-- Structure de la table `character_appearances`
--

CREATE TABLE IF NOT EXISTS `character_appearances` (
  `object_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'ðáðêðáðàðáÐæðáÐöðá┬░ðá┬╗ðíðèðáðàðíÔÇ╣ðáÔäû ðáÐæðáÊæ ðáÐæðáÐûðíðéðáÐòðáÐöðá┬░.',
  `face_color` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `face_skin` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `adorments_skin` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `features_skin` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `features_color` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `voice` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `bone_structure_brow` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `bone_structure_cheekbones` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `bone_structure_jaw` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `bone_structure_jaw_jut` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `ears_rotation` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `ears_extension` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `ears_trim` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `ears_size` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `eyes_width` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `eyes_height` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `eyes_separation` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `eyes_angle` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `eyes_inner_brow` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `eyes_outer_brow` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `nose_extension` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `nose_size` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `nose_bridge` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `nose_nostril_width` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `nose_tip_width` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `nose_tip` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `nose_nostril_flare` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `mouth_pucker` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `mouth_position` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `mouth_width` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `mouth_lip_thickness` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `mouse_corners` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `eyes_shape` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `nose_bend` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `bone_structure_jaw_width` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `mouth_gape` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`object_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='ðóð░ð▒ð╗ð©Ðåð░ ð▓ð¢ðÁÐêð¢ð¥ÐüÐéð© ð©ð│ÐÇð¥ð║ð¥ð▓.';


-- --------------------------------------------------------

--
-- Structure de la table `character_faces`
--

CREATE TABLE IF NOT EXISTS `character_faces` (
  `objectId` int(11) NOT NULL DEFAULT '0',
  `faceColor` int(10) NOT NULL DEFAULT '0',
  `hairColor` int(10) NOT NULL DEFAULT '0',
  `eyebrowsFirstVal` int(10) NOT NULL DEFAULT '0',
  `eyebrowsSecondVal` int(10) NOT NULL DEFAULT '0',
  `eyebrowsThridVal` int(10) NOT NULL DEFAULT '0',
  `eyeFirstVal` int(10) NOT NULL DEFAULT '0',
  `eyeSecondVal` int(10) NOT NULL DEFAULT '0',
  `eyeThridVal` int(10) NOT NULL DEFAULT '0',
  `eyePosVertical` int(10) NOT NULL DEFAULT '0',
  `eyeWidth` int(10) NOT NULL DEFAULT '0',
  `eyeHeight` int(10) NOT NULL DEFAULT '0',
  `chin` int(10) NOT NULL DEFAULT '0',
  `cheekbonePos` int(10) NOT NULL DEFAULT '0',
  `earsFirstVal` int(10) NOT NULL DEFAULT '0',
  `earsSecondVal` int(10) NOT NULL DEFAULT '0',
  `earsThridVal` int(10) NOT NULL DEFAULT '0',
  `earsFourthVal` int(10) NOT NULL DEFAULT '0',
  `noseFirstVal` int(10) NOT NULL DEFAULT '0',
  `noseSecondVal` int(10) NOT NULL DEFAULT '0',
  `noseThridVal` int(10) NOT NULL DEFAULT '0',
  `noseFourthVal` int(10) NOT NULL DEFAULT '0',
  `noseFifthVal` int(10) NOT NULL DEFAULT '0',
  `lipsFirstVal` int(10) NOT NULL DEFAULT '0',
  `lipsSecondVal` int(10) NOT NULL DEFAULT '0',
  `lipsThridVal` int(10) NOT NULL DEFAULT '0',
  `lipsFourthVal` int(10) NOT NULL DEFAULT '0',
  `lipsFifthVal` int(10) NOT NULL DEFAULT '0',
  `lipsSixthVal` int(10) NOT NULL DEFAULT '0',
  `cheeks` int(10) NOT NULL DEFAULT '0',
  `bridgeFirstVal` int(10) NOT NULL DEFAULT '0',
  `bridgeSecondVal` int(10) NOT NULL DEFAULT '0',
  `bridgeThridVal` int(10) NOT NULL DEFAULT '0',
  `temp1` int(10) NOT NULL DEFAULT '0',
  `temp2` int(10) NOT NULL DEFAULT '0',
  `temp3` int(10) NOT NULL DEFAULT '0',
  `temp4` int(10) NOT NULL DEFAULT '0',
  `temp5` int(10) NOT NULL DEFAULT '0',
  `temp6` int(10) NOT NULL DEFAULT '0',
  `temp7` int(10) NOT NULL DEFAULT '0',
  `temp8` int(10) NOT NULL DEFAULT '0',
  `temp9` int(10) NOT NULL DEFAULT '0',
  `temp10` int(10) NOT NULL DEFAULT '0',
  `temp11` int(10) NOT NULL DEFAULT '0',
  `temp12` int(10) NOT NULL DEFAULT '0',
  `temp13` int(10) NOT NULL DEFAULT '0',
  `temp14` int(10) NOT NULL DEFAULT '0',
  `temp15` int(10) NOT NULL DEFAULT '0',
  `temp16` int(10) NOT NULL DEFAULT '0',
  `temp17` int(10) NOT NULL DEFAULT '0',
  `temp18` int(10) NOT NULL DEFAULT '0',
  `temp19` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`objectId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `character_friends`
--

CREATE TABLE IF NOT EXISTS `character_friends` (
  `object_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'ðúð¢ð©ð║ð░ð╗Ðîð¢Ðïð╣ ð©ð┤ ð©ð│ÐÇð¥ð║ð░.',
  `friend_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'ðúð¢ð©ð║ð░ð╗Ðîð¢Ðïð╣ ð©ð┤ ð┤ÐÇÐâð│ð░.',
  `friend_note` varchar(45) NOT NULL COMMENT 'ðƒð¥ð╝ðÁÐéð║ð░ ð¥ð▒ ð┤ÐÇÐâð│ðÁ.',
  PRIMARY KEY (`object_id`,`friend_id`),
  KEY `select` (`object_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='ðóð░ð▒ð╗ð©Ðåð░ ð┤ÐÇÐâðÀðÁð╣ ð©ð│ÐÇð¥ð║ð¥ð▓.';

-- --------------------------------------------------------

--
-- Structure de la table `character_hotkey`
--

CREATE TABLE IF NOT EXISTS `character_hotkey` (
  `object_id` int(11) NOT NULL DEFAULT '0' COMMENT 'ðáðêðáðàðáÐæðáÐöðá┬░ðá┬╗ðíðèðáðàðíÔÇ╣ðáÔäû ðáÐæðáÊæ ðáÐæðáÐûðíðéðáÐòðáÐöðá┬░.',
  `data` blob COMMENT 'ðôð¥ÐÇÐÅÐçð©ðÁ ð║ð╗ð░ð▓ð©ÐêÐï',
  PRIMARY KEY (`object_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='ðáÐûðáÐòðíðéðíðÅðíÔÇíðáÐæðá┬Á ðáÐöðáðàðáÐòðáÐùðáÐöðáÐæ.';
-- --------------------------------------------------------

--
-- Structure de la table `character_inventors`
--

CREATE TABLE IF NOT EXISTS `character_inventors` (
  `owner_id` int(11) unsigned NOT NULL DEFAULT '0',
  `id` int(11) unsigned NOT NULL DEFAULT '0',
  `level` smallint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`owner_id`,`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `character_quests`
--

CREATE TABLE IF NOT EXISTS `character_quests` (
  `object_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ðáÐøðá┬▒ðáÊæðá┬Âðá┬ÁðáÐöðíÔÇÜ ðáÐæðáÊæ ðáÐæðáÐûðíðéðáÐòðáÐöðá┬░.',
  `quest_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'ðá┬ÿðáÊæ ðáÐöðáðåðá┬ÁðíðâðíÔÇÜðá┬░.',
  `state` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT 'ðáðÄðíÔÇÜðá┬░ðáÊæðáÐæðíðÅ ðáÐöðáðåðá┬ÁðíðâðíÔÇÜðá┬░.',
  `date` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT 'ðáÔÇØðá┬░ðíÔÇÜðá┬░ ðá┬Àðá┬░ðáðåðá┬ÁðíðéðíÔé¼ðá┬ÁðáðàðáÐæðíðÅ ðáÐöðáðåðá┬ÁðíðâðíÔÇÜðá┬░.',
  `panel_state` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT 'ð×Ðéð¥ð▒ÐÇð░ðÂð░ÐéÐî ð╗ð© ð¢ð░ ð┐ð░ð¢ðÁð╗ð©.',
  PRIMARY KEY (`object_id`,`quest_id`) USING BTREE
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='ðáÐ×ðá┬░ðá┬▒ðá┬╗ðáÐæðíÔÇáðá┬░ ðáÐöðáðåðá┬ÁðíðâðíÔÇÜðáÐòðáðå ðáÐæðáÐûðíðéðáÐòðáÐöðáÐòðáðå.' AUTO_INCREMENT=270401657 ;

--
-- Contenu de la table `character_quests`
--

-- --------------------------------------------------------

--
-- Structure de la table `character_quest_vars`
--

CREATE TABLE IF NOT EXISTS `character_quest_vars` (
  `object_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ðáÐøðá┬▒ðáÊæðá┬ÁðáÐöðíÔÇÜ ðáÐæðáÊæ ðáÐæðáÐûðíðéðáÐòðáÐöðá┬░.',
  `quest_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'ðá┬ÿðáÊæ ðáÐöðáðåðá┬ÁðíðâðíÔÇÜðá┬░.',
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT 'ðáÐ£ðá┬░ðá┬Àðáðåðá┬░ðáðàðáÐæðá┬Á ðáÐùðá┬Áðíðéðá┬ÁðáÐÿðá┬ÁðáðàðáðàðáÐòðáÔäû.',
  `value` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'ðáÔÇöðáðàðá┬░ðíÔÇíðá┬ÁðáðàðáÐæðá┬Á ðáÐùðá┬Áðíðéðá┬ÁðáÐÿðá┬ÁðáðàðáðàðáÐòðáÔäû.',
  PRIMARY KEY (`object_id`,`quest_id`,`name`),
  KEY `key_id` (`quest_id`,`object_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='ðáÐ×ðá┬░ðá┬▒ðá┬╗ðáÐæðíÔÇáðá┬░ ðáÐùðá┬Áðíðéðá┬ÁðáÐÿðá┬ÁðáðàðáðàðíÔÇ╣ðíÔÇª ðáÐöðáðåðá┬ÁðíðâðíÔÇÜðáÐòðáðå.' AUTO_INCREMENT=270336036 ;


-- --------------------------------------------------------

--
-- Structure de la table `character_save_effects`
--

CREATE TABLE IF NOT EXISTS `character_save_effects` (
  `object_id` int(11) NOT NULL DEFAULT '0',
  `class_id` tinyint(3) NOT NULL DEFAULT '0',
  `skill_id` int(11) NOT NULL DEFAULT '0',
  `effect_order` tinyint(3) NOT NULL DEFAULT '0',
  `count` int(11) NOT NULL DEFAULT '0',
  `duration` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`object_id`,`class_id`,`skill_id`,`effect_order`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Structure de la table `character_settings`
--

CREATE TABLE IF NOT EXISTS `character_settings` (
  `object_id` int(11) NOT NULL DEFAULT '0' COMMENT 'ðúð¢ð©ð║ð░ð╗Ðîð¢Ðïð╣ ð©ð┤ ð©ð│ÐÇð¥ð║ð░.',
  `data` blob COMMENT 'ðÿð¢Ðäð¥ÐÇð╝ð░Ðåð©ÐÅ ð¥ ð¢ð░ÐüÐéÐÇð¥ð╣ð║ð░Ðà.',
  PRIMARY KEY (`object_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='ðØð░ÐüÐéÐÇð¥ð╣ð║ð© ð║ð╗ð©ðÁð¢Ðéð░ ð©ð│ÐÇð¥ð║ð¥ð▓.';

-- --------------------------------------------------------

--
-- Structure de la table `character_skills`
--

CREATE TABLE IF NOT EXISTS `character_skills` (
  `object_id` int(11) NOT NULL DEFAULT '0',
  `class_id` tinyint(3) NOT NULL DEFAULT '0',
  `skill_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`object_id`,`class_id`,`skill_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `character_skills`
--

-- --------------------------------------------------------

--
-- Structure de la table `character_skill_reuses`
--

CREATE TABLE IF NOT EXISTS `character_skill_reuses` (
  `object_id` int(11) NOT NULL DEFAULT '0',
  `skill_id` int(11) NOT NULL DEFAULT '0',
  `item_id` int(11) NOT NULL DEFAULT '0',
  `end_time` bigint(110) NOT NULL DEFAULT '0',
  PRIMARY KEY (`object_id`,`skill_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `character_territories`
--

CREATE TABLE IF NOT EXISTS `character_territories` (
  `object_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'ðúð¢ð©ð║ð░ð╗Ðîð¢Ðïð╣ ð©ð┤ ð©ð│ÐÇð¥ð║ð░.',
  `territory_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'ðÿð┤ ÐéðÁÐÇÐÇð©Ðéð¥ÐÇð©ð©, ð▓ ð║ð¥Ðéð¥ÐÇð¥ð╣ ð¥ð¢ ð┐ð¥ð▒Ðïð▓ð░ð╗',
  UNIQUE KEY `key_territory` (`territory_id`,`object_id`),
  KEY `key_player` (`object_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='ðóð░ð▒ð╗ð©Ðåð░ ÐéðÁÐÇÐÇð©Ðéð¥ÐÇð©ð╣, ð▓ ð║ð¥Ðéð¥ÐÇÐïÐà ð┐ð¥ð▒Ðïð▓ð░ð╗ ð©ð│ÐÇð¥ð║.';


-- --------------------------------------------------------

--
-- Structure de la table `character_variables`
--

CREATE TABLE IF NOT EXISTS `character_variables` (
  `object_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'ðúð¢ð©ð║ð░ð╗Ðîð¢Ðïð╣ ð©ð┤ ð©ð│ÐÇð¥ð║ð░.',
  `var_name` varchar(45) NOT NULL DEFAULT '' COMMENT 'ðØð░ðÀð▓ð░ð¢ð©ðÁ ð┐ðÁÐÇðÁð╝ðÁð¢ð¢ð¥ð╣.',
  `var_value` varchar(45) NOT NULL DEFAULT '' COMMENT 'ðùð¢ð░ÐçðÁð¢ð©ðÁ ð┐ðÁÐÇðÁð╝ðÁð¢ð¢ð¥ð╣.',
  PRIMARY KEY (`object_id`,`var_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='ðóð░ð▒ð╗ð©Ðåð░ ð┐ðÁÐÇðÁð╝ðÁð¢ð¢ÐïÐà ð©ð│ÐÇð¥ð║ð¥ð▓.';

-- --------------------------------------------------------

--
-- Structure de la table `guilds`
--

CREATE TABLE IF NOT EXISTS `guilds` (
  `id` int(11) NOT NULL DEFAULT '0' COMMENT 'ðúð¢ð©ð║ð░ð╗Ðîð¢Ðïð╣ ð©ð┤ ð║ð╗ð░ð¢ð░.',
  `name` varchar(45) NOT NULL COMMENT 'ðØð░ðÀð▓ð░ð¢ð©ðÁ ð║ð╗ð░ð¢ð░.',
  `title` varchar(45) NOT NULL COMMENT 'ðóð©ÐéÐâð╗ ð│ð©ð╗Ðîð┤ð©ð©.',
  `level` smallint(6) NOT NULL DEFAULT '0' COMMENT 'ðúÐÇð¥ð▓ðÁð¢Ðî ð║ð╗ð░ð¢ð░.',
  `icon` blob COMMENT 'ðÿð║ð¥ð¢ð║ð░ ð║ð╗ð░ð¢ð░.',
  `icon_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'ðØð░ðÀð▓ð░ð¢ð©ðÁ ð©ð║ð¥ð¢ð║ð© ð│ð©ð╗Ðîð┤ð©ð©.',
  `message` varchar(255) NOT NULL DEFAULT '' COMMENT 'ðáÔÇóðá┬Âðá┬ÁðáÊæðáðàðáðåðáðàðáÐòðá┬Á ðíðâðáÐòðáÐòðá┬▒ðíÔÇ░ðá┬ÁðáðàðáÐæðá┬Á ðáÐûðáÐæðá┬╗ðíðèðáÊæðáÐæðáÐæ.',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='ðóð░ð▒ð╗ð©Ðåð░ ð║ð╗ð░ð¢ð¥ð▓';

-- --------------------------------------------------------

--
-- Structure de la table `guild_ranks`
--

CREATE TABLE IF NOT EXISTS `guild_ranks` (
  `guild_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ðÿð┤ ð│ð©ð╗Ðîð┤ð©ð©.',
  `rank_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'ðØð░ðÀð▓ð░ð¢ð©ðÁ ÐÇð░ð¢ð│ð░.',
  `order` tinyint(3) unsigned NOT NULL COMMENT 'ðÿð¢ð┤ðÁð║Ðü ÐÇð░ð¢ð│ð░.',
  `law` tinyint(3) unsigned NOT NULL COMMENT 'ðØð░ð▒ð¥ÐÇ ð┐ÐÇð░ð▓.',
  KEY `guild_id` (`guild_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='ðóð░ð▒ð╗ÐéÐåð░ ÐÇð░ð¢ð│ð¥ð▓ ð│ð©ð╗Ðîð┤ð©ð╣.' AUTO_INCREMENT=268436103 ;

-- --------------------------------------------------------

--
-- Structure de la table `items`
--

CREATE TABLE IF NOT EXISTS `items` (
  `object_id` int(10) unsigned NOT NULL DEFAULT '0',
  `owner_id` int(10) unsigned NOT NULL DEFAULT '0',
  `owner_name` varchar(45) NOT NULL DEFAULT '',
  `item_id` int(10) unsigned NOT NULL DEFAULT '0',
  `item_count` bigint(20) unsigned NOT NULL DEFAULT '0',
  `masterworked` int(11) NOT NULL DEFAULT '0',
  `enchant_level` smallint(5) NOT NULL DEFAULT '0',
  `bonus_id` int(10) NOT NULL DEFAULT '0',
  `autor` varchar(255) NOT NULL DEFAULT '',
  `location` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `index` smallint(6) NOT NULL DEFAULT '0',
  `has_crystal` tinyint(1) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`object_id`) USING HASH,
  KEY `key_owner_id` (`owner_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 PACK_KEYS=1;

--
-- Contenu de la table `items`
--


--
-- Structure de la table `region_status`
--

CREATE TABLE IF NOT EXISTS `region_status` (
  `region_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'ðÿð┤ ÐÇðÁð│ð©ð¥ð¢ð░.',
  `owner_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'ðÿð┤ ð▓ð╗ð░ð┤ðÁÐÄÐëðÁð╣ ð│ð©ð╗Ðîð┤ð©ð©.',
  `state` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'ðíð¥ÐüÐéð¥ÐÅð¢ð©ðÁ ÐÇðÁð│ð©ð¥ð¢ð░.',
  PRIMARY KEY (`region_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='ðóð░ð▒ð╗ð©Ðåð░ ÐüÐéð░ÐéÐâÐüð¥ð▓ ÐÇðÁð│ð©ð¥ð¢ð¥ð▓.';

--
-- Contenu de la table `region_status`
--

INSERT INTO `region_status` (`region_id`, `owner_id`, `state`) VALUES
(400, 0, 0),
(401, 0, 0),
(402, 0, 0),
(403, 0, 0);

-- --------------------------------------------------------

--
-- Structure de la table `region_war_register`
--

CREATE TABLE IF NOT EXISTS `region_war_register` (
  `region_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'ðÿð┤ ÐÇðÁð│ð©ð¥ð¢ð░.',
  `guild_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'ðÿð┤ ð│ð©ð╗Ðîð┤ð©ð©.',
  PRIMARY KEY (`region_id`,`guild_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='ðóð░ð▒ð╗ð©Ðåð░ ÐÇðÁð│ð©ÐüÐéÐÇð░Ðåð©ð© ð¢ð░ ð▒ð©Ðéð▓Ðï ðÀð░ ÐÇðÁð│ð©ð¥ð¢Ðï.';

-- --------------------------------------------------------

--
-- Structure de la table `server_variables`
--

CREATE TABLE IF NOT EXISTS `server_variables` (
  `var_name` varchar(45) NOT NULL DEFAULT '' COMMENT 'ðØð░ðÀð▓ð░ð¢ð©ðÁ ð┐ðÁÐÇðÁð╝ðÁð¢ð¢ð¥ð╣.',
  `var_value` varchar(45) NOT NULL DEFAULT '' COMMENT 'ðùð¢ð░ÐçðÁð¢ð©ðÁ ð┐ðÁÐÇðÁð╝ðÁð¢ð¢ð¥ð╣.',
  PRIMARY KEY (`var_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='ðóð░ð▒ð╗ð©Ðåð░ ÐüðÁÐÇð▓ðÁÐÇð¢ÐïÐà ð┐ðÁÐÇðÁð╝ðÁð¢ð¢ÐïÐà.';

-- --------------------------------------------------------

--
-- Structure de la table `skill_learns`
--

CREATE TABLE IF NOT EXISTS `skill_learns` (
  `classId` tinyint(3) NOT NULL DEFAULT '0',
  `skillId` int(11) NOT NULL DEFAULT '0',
  `minLevel` smallint(6) NOT NULL DEFAULT '0',
  `price` int(11) NOT NULL DEFAULT '0',
  `replaceId` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`classId`,`skillId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `wait_items`
--

CREATE TABLE IF NOT EXISTS `wait_items` (
  `order` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ðá┬áðíÐÜðá┬áðíÔÇóðá┬áðí┬ÿðá┬áðÆ┬ÁðáðÄðáÔÇÜ ðá┬áðáÔÇáðáðÄð▓ðéÔäûðá┬áðóÔÇÿðá┬áðÆ┬░ðáðÄð▓ðéðÄðá┬áðíÔÇÿ.',
  `emptor` varchar(45) CHARACTER SET utf8 NOT NULL COMMENT 'ðáÔÇ║ðáÐòðáÐûðáÐæðáðà ðáÐùðáÐòðáÐöðíÐôðáÐùðá┬░ðíÔÇÜðá┬Áðá┬╗ðíðÅ.',
  `char_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT 'ðá┬áðÆ┬ÿðá┬áðí┬ÿðáðÄðáðÅ ðá┬áðíÔÇÿðá┬áðíÔÇôðáðÄðáÔÇÜðá┬áðíÔÇóðá┬áðíÔÇØðá┬áðÆ┬░.',
  `item_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'ðá┬áðÆ┬ÿðá┬áðóÔÇÿ ðá┬áðíÔÇÿðáðÄð▓ðéÐÖðá┬áðÆ┬Áðá┬áðí┬ÿðá┬áðÆ┬░.',
  `item_count` int(10) unsigned NOT NULL DEFAULT '1' COMMENT 'ðá┬áðíÔäóðá┬áðíÔÇóðá┬áðÆ┬╗-ðá┬áðáÔÇáðá┬áðíÔÇó ðá┬áðíÔÇÿðáðÄð▓ðéÐÖðá┬áðÆ┬Áðá┬áðí┬ÿðá┬áðíÔÇóðá┬áðáÔÇá.',
  `enchant_level` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`order`,`char_name`,`item_id`,`item_count`) USING BTREE,
  KEY `name_key` (`char_name`),
  KEY `order_key` (`order`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='ðá┬áðÆ┬ÿðáðÄð▓ðéÐÖðá┬áðÆ┬Áðá┬áðí┬ÿðáðÄð▓ðéÔäû ðá┬áðáÔÇáðá┬áðíÔÇóðá┬áðÆ┬Âðá┬áðíÔÇÿðá┬áðóÔÇÿðá┬áðÆ┬░ðáðÄðáÔÇ╣ðáðÄð▓ðé┬░ðá┬áðíÔÇÿ' AUTO_INCREMENT=3217 ;

-- --------------------------------------------------------

--
-- Structure de la table `wait_skills`
--

CREATE TABLE IF NOT EXISTS `wait_skills` (
  `order` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ðÿð┤ ðÀð░ð┐ð©Ðüð©.',
  `char_name` varchar(45) NOT NULL COMMENT 'ðÿð╝ÐÅ ð┐ðÁÐÇÐüð¥ð¢ð░ðÂð░.',
  `skill_id` int(10) unsigned NOT NULL COMMENT 'ðÿð┤ Ðüð║ð©ð╗ð░.',
  `skill_class` int(10) NOT NULL COMMENT 'ðÜð╗ð░ÐüÐü Ðüð║ð©ð╗ð░.',
  PRIMARY KEY (`order`),
  KEY `name_key` (`char_name`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='ðóð░ð▒ð╗ð©Ðåð░ ð¥ðÂð©ð┤ð░ÐÄÐëð©Ðà ð▓Ðïð┤ð░Ðçð© Ðüð║ð©ð╗ð¥ð▓.' AUTO_INCREMENT=289 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
