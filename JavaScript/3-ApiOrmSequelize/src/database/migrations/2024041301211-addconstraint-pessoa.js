'use strict';
/** @type {import('sequelize-cli').Migration} */
module.exports = {
  async up(queryInterface, Sequelize) {
    await queryInterface.addConstraint('pessoas', {
      allowNull: false,
      unique: true
    });
  },
  async down(queryInterface, Sequelize) {
    await queryInterface.removeConstraint('pessoas', 'allowNull');
    await queryInterface.removeConstraint('pessoas', 'unique');
  }
};
